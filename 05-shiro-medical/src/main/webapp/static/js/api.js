const service = axios.create({
    baseURL:'',
    timeout:5000,
});

/**
 * 请求拦截器
 * */
service.interceptors.request.use(
    //在发送请求之前做些什么
    function (config){
        return config;
    },
    //在请求错误时做些什么
    function (error) {
        return Promise.reject(error);
    });

/**
 * 响应拦截器
 * */
service.interceptors.response.use(
    //对响应数据做些什么
    function (response){
        return response.data;
    },
    //对响应错误做些什么
    function (error) {
        return Promise.reject(error);
    });

/**
 * 按页查询
 * */
function findUserByPage(currentPage=1,pageSize=10){
    return service({
        url:'userServlet?action=queryByPage',
        method:'get',
        params:{currentPage,pageSize}
    });
}

/**
 * 删除指定id的用户记录
 * */
function deleteUserById(id) {
    return service({
        url: 'userServlet?action=deleteUserById',
        method: 'POST',
        params: {id}
    })
}

/**
 * 添加用户
 * */
function insertUser(data){
    return service({
        url:'userServlet?action=addUser',
        method:'post',
        params:data
    });
}

/**
 * 批量删除用户信息
 * */
function removeUserByIds(ids){
    return service({
        //请求参数，即？后面的为请求字符串
        url:'userServlet?action=deleteUserByIds',
        method:'post',
        data:ids
    });
}

/**
 * 发送请求，获取所有的部门信息列表
 * */
function queryAllDepartment(){
    return service({
        url:'departmentServlet?action=queryDeptTree',
        method:'get',
    });
}

/**
 * 生成部门树
 * */
async function makeDepartmentTree(){
    let arr=[];
    await queryAllDepartment().then((response)=>{
        arr=response.data;
    });
    //生成数组树
    return makeDeptTree(arr);
}

/**
 * 递归调用生成树
 * */
function makeDeptTree(arr){
    //去遍历数组重新封装一个新符合要求的数组
    let newArr=[];
    arr.forEach(dept=>{
        let newDept={};
        let {dept_id,dept_name}=dept;
        newDept.dataAttrs=[{title:'dept-id',data:dept_id}];
        newDept.title=dept_name;
        //去获取子集
        let children=dept.children;
        if(children && children.length>0){
            //去遍历children
            let newChildren=makeDeptTree(children);
            newDept.data=newChildren;
        }
        newArr.push(newDept);
    });
    return newArr;
}

/**
 * 查询，根据用户名获取用户信息
 * */
function findByUserName(username){
    return service({
        url:'userServlet?action=queryByUsername',
        method:'get',
        //请求参数是一个QueryString
        params:{username}
    });
}

/**
 * 登出功能
 * */
function handlerLogout(){
    return service({
        url:'handlerLogin?action=doLogout',
        method:'post'
    });
}

/**
 * 头像上传
 * */
function avatarAjaxUpload(data) {
    return service({
        url: 'userServlet?action=doAvatarUpload',
        method: 'POST',
        headers: {'Content-Type': 'multipart/form-data'},
        data
    })
}
var C=Object.defineProperty;var F=(t,u,r)=>u in t?C(t,u,{enumerable:!0,configurable:!0,writable:!0,value:r}):t[u]=r;var i=(t,u,r)=>(F(t,typeof u!="symbol"?u+"":u,r),r);import{defineStore as n}from"https://esm.sh/pinia@^2.0.23";import l from"https://esm.sh/js-cookie@^3.0.1";import p from"https://esm.sh/axios@^1.1.2";import{sha256 as g}from"https://esm.run/js-sha256@^0.9.0";const _={ACCOUNT_PWD_NULL_OR_EMPTY:"\u8D26\u53F7\u6216\u5BC6\u7801\u4E3A\u7A7A",ACCOUNT_OR_PWD_ERROR:"\u8D26\u53F7\u6216\u5BC6\u7801\u9519\u8BEF",LOGIN_SUCCESS:"\u767B\u5F55\u6210\u529F",NAME_ID_NUM_PWD_NULL_OR_EMPTY:"\u59D3\u540D\uFF0C\u8EAB\u4EFD\u8BC1\u6216\u5BC6\u7801\u4E3A\u7A7A",ENTER_TWICE_PWD_MISMATCH:"\u4E24\u6B21\u8F93\u5165\u7684\u5BC6\u7801\u4E0D\u5339\u914D\uFF01",ID_NUM_REPEATED:"\u8EAB\u4EFD\u8BC1\u53F7\u91CD\u590D",REG_SUCCESS:"\u6CE8\u518C\u6210\u529F",ID_NUM_FORMAT_ERROR:"\u8EAB\u4EFD\u8BC1\u53F7\u683C\u5F0F\u9519\u8BEF",LOGIN_REPEAT:"\u91CD\u590D\u767B\u5F55",NOT_LOGGED_ON:"\u60A8\u8FD8\u672A\u767B\u5F55",APPLY_SUCCESS:"\u7533\u8BF7\u6210\u529F",APPLY_FAILED:"\u7533\u8BF7\u5931\u8D25\uFF0C\u60A8\u4E0D\u7B26\u5408\u7533\u8BF7\u6761\u4EF6",APPLY_REPEAT:"\u91CD\u590D\u7533\u8BF7",DOES_NOT_APPLY:"\u6CA1\u6709\u7533\u8BF7\u6D3B\u52A8",SECKILL_NOT_STARTED:"\u79D2\u6740\u6D3B\u52A8\u8FD8\u672A\u5F00\u59CB",SECKILL_ENDED:"\u79D2\u6740\u6D3B\u52A8\u5DF2\u7ECF\u7ED3\u675F",PRODUCT_SOLD_OUT:"\u5546\u54C1\u5DF2\u7ECF\u552E\u5B8C",PURCHASE_REPEAT:"\u91CD\u590D\u8D2D\u4E70",INVALID_SECKILL_URL:"\u79D2\u6740\u94FE\u63A5\u9519\u8BEF",SECKILL_SUCCESS:"\u79D2\u6740\u6210\u529F\uFF0C\u6B63\u5728\u83B7\u53D6\u8BA2\u5355",PUT_ORDER_FAILED:"\u4E0B\u8BA2\u5355\u5931\u8D25",PURCHASE_SUCCESS:"\u652F\u4ED8\u6210\u529F\uFF0C\u5DF2\u4ECE\u60A8\u7684\u9ED8\u8BA4\u8D26\u53F7\u4E2D\u6263\u6B3E\uFF01",PURCHASE_FAILED:"\u652F\u4ED8\u5931\u8D25\uFF0C\u8BF7\u91CD\u65B0\u652F\u4ED8\uFF01"},e=p.create({timeout:1e4,withCredentials:!0,baseURL:"http://localhost:9000"});e.interceptors.request.use(t=>t);e.interceptors.response.use(t=>{const u=t.data;return"code"in u&&"data"in u&&"message"in u?(u.code===500&&console.error(u.message),u):(console.error("\u8BF7\u6C42\u8FD4\u56DE\u503C\u7C7B\u578B\u9519\u8BEF\uFF01"),null)},t=>{console.error(t)});class c{static loginByAccount(u){return e.post("/customer/login",{account:u.account,password:u.password})}static loginByIdNumber(u){return e.post("/customer/login",{idNumber:u.idNumber,password:u.password})}static register(u){return e.post("/customer/register",u)}static logout(){return e.post("/customer/logout")}static getInfo(){return e.get("/customer/getInfo")}}class E{static hash(u){return g(u)}}class B{static validIdNum(u){return(u==null?void 0:u.length)===18&&/^\d+$/.test(u)||/^\d+$/.test(u==null?void 0:u.substring(0,17))&&/^[A-Za-z]+$/.test(u==null?void 0:u.charAt(17))}}const A=n("customer",{state:()=>({name:"",canApply:!1,applied:!1}),actions:{clearAll(){this.name="",this.canApply=!1,this.applied=!1,console.warn("customer: clear store!")},login(t){const{accountOrIdNumber:u}=t;return t.password=E.hash(t.password),B.validIdNum(u)?(t.idNumber=u,c.loginByIdNumber(t)):(t.account=u,c.loginByAccount(t))},register(t){return t.password=E.hash(t.password),c.register(t)},logout(){return c.logout()},async getInfo(){if(this.name!=="")return console.log("customer: get from cache!"),{code:200,data:this.$state};const t=await c.getInfo();return t.code===200&&(this.$state=t.data),t}}});class o{}i(o,"TOKEN_NAME","token");class D{static get(){return l.get(o.TOKEN_NAME)}static delete(){l.remove(o.TOKEN_NAME)}}class s{static getCurrent(){return e.get("/seckillActivity/getCurrent")}static apply(){return e.post("/seckillActivity/apply")}static getCountDown(){return e.get("/seckillActivity/getCountDown")}static getSeckillUrl(){return e.get("/seckillActivity/getSeckillUrl")}static seckill(u){return e.post("/seckillActivity/seckill/"+u)}static getOrder(){return e.get("/seckillActivity/getOrder")}static pay(u){return e.post("/seckillActivity/pay","orderId="+u)}}const y=A(),S=n("activity",{state:()=>({activity:{}}),actions:{clearAll(){this.activity={},console.warn("apply: clear store!")},async getCurrent(){if(Object.keys(this.activity).length)return console.log("activity: get from cache!"),{code:200,data:this.activity};const t=await s.getCurrent();return t.code===200&&(this.activity=t.data),t},async apply(){const t=await s.apply();return(t.code===200||t.message===_.APPLY_REPEAT)&&(y.applied=!0,console.log("apply: update cache (customer.applied -> true)")),t},getCountDown(){return s.getCountDown()},getSeckillUrl(){return s.getSeckillUrl()},seckill(t){return s.seckill(t)},getOrder(){return s.getOrder()},pay(t){return s.pay(t)}}}),d=S(),a=A(),R=n("settings",{actions:{async logout(){await a.logout(),D.delete(),d.clearAll(),a.clearAll()},verifyLogin(){return!(a.name===""&&!D.get())}}});export{_ as M,R as a,S as b,A as u};

"use strict";(self["webpackChunkseckill_system_web"]=self["webpackChunkseckill_system_web"]||[]).push([[116],{224:function(e,t,a){a.d(t,{Z:function(){return r}});class r{static nullOrEmpty(){for(let e of arguments){if(void 0===e)return!0;if("string"===typeof e&&""===e)return!0}return!1}}},116:function(e,t,a){a.r(t),a.d(t,{default:function(){return _}});var r=a(311);const s=e=>((0,r.pushScopeId)("data-v-04fa5a24"),e=e(),(0,r.popScopeId)(),e),o={class:"main"},c={class:"register-view"},l=s((()=>(0,r.createElementVNode)("p",{class:"title"},"账户注册",-1))),n={class:"register-form"},d=s((()=>(0,r.createElementVNode)("p",{class:"account-text"},"用户姓名",-1))),i=s((()=>(0,r.createElementVNode)("p",{class:"id-number-text"},"身份证号码",-1))),u=s((()=>(0,r.createElementVNode)("p",{class:"password-text"},"密码",-1))),p=s((()=>(0,r.createElementVNode)("p",{class:"password-text2"},"确认密码",-1))),m={class:"back-text"},w=(0,r.createTextVNode)(" 已有账户，"),N=(0,r.createTextVNode)("直接登录");function f(e,t,a,s,f,h){const E=(0,r.resolveComponent)("router-link");return(0,r.openBlock)(),(0,r.createElementBlock)("div",o,[(0,r.createElementVNode)("div",c,[l,(0,r.createElementVNode)("form",n,[d,(0,r.withDirectives)((0,r.createElementVNode)("input",{"onUpdate:modelValue":t[0]||(t[0]=t=>e.name=t),type:"text",class:"account-bar",placeholder:"请输入您的真实姓名"},null,512),[[r.vModelText,e.name]]),i,(0,r.withDirectives)((0,r.createElementVNode)("input",{"onUpdate:modelValue":t[1]||(t[1]=t=>e.idNumber=t),type:"text",class:"id-number-bar",placeholder:"请输入您的身份证号码"},null,512),[[r.vModelText,e.idNumber]]),u,(0,r.withDirectives)((0,r.createElementVNode)("input",{"onUpdate:modelValue":t[2]||(t[2]=t=>e.password=t),type:"password",class:"password-bar",placeholder:"请设置您的密码",autocomplete:"off"},null,512),[[r.vModelText,e.password]]),p,(0,r.withDirectives)((0,r.createElementVNode)("input",{"onUpdate:modelValue":t[3]||(t[3]=t=>e.checkPassword=t),onKeyup:t[4]||(t[4]=(0,r.withKeys)(((...e)=>h.register&&h.register(...e)),["enter"])),type:"password",class:"password-bar2",placeholder:"请再次确认您的密码",autocomplete:"off"},null,544),[[r.vModelText,e.checkPassword]])]),(0,r.createElementVNode)("button",{onClick:t[5]||(t[5]=(...e)=>h.register&&h.register(...e)),class:"register-button"},"注册"),(0,r.createElementVNode)("p",m,[w,(0,r.createVNode)(E,{to:"/"},{default:(0,r.withCtx)((()=>[N])),_:1})])])])}var h=a(163),E=a(224),v={name:"Register",data:()=>({name:"",idNumber:"",password:"",checkPassword:""}),methods:{async register(){const{name:e,idNumber:t,password:a,checkPassword:r}=this;if(E.Z.nullOrEmpty(e,t,a,r))return void await alert(h.Z.NAME_ID_NUM_PWD_NULL_OR_EMPTY);if(a!==r)return void await alert(h.Z.ENTER_TWICE_PWD_MISMATCH);let s=await this.$store.dispatch("customer/register",{name:e,idNumber:t,password:a});500!==s.code&&(await alert("注册成功，您的账号为："+s.data.accountId+"，正在为您跳转到登录界面..."),await this.$router.push("/"))}}},V=a(89);const b=(0,V.Z)(v,[["render",f],["__scopeId","data-v-04fa5a24"]]);var _=b}}]);
//# sourceMappingURL=116.bede9470.js.map
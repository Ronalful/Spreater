<#import "parts/common.ftlh" as c>
<@c.page>
User editor
<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}"/>
    <input type="hidden" name="userId" value="${user.id}"/>
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
    </#list>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
</form>
</@c.page>

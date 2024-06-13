<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
    <@l.logout/>
</div>
<div>
    <form method="post" action="/main">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="text" placeholder="Введите сообщение">
        <input type="text" name="tag" placeholder="Тэг">
        <button type="submit">Добавить</button>
    </form>
</div>
<div>
    <h3>Фильтр</h3>
    <form method="get">
        <input type="text" name="filter" value="${filter}" placeholder="Введите фильтр">
        <button type="submit">Найти</button>
    </form>
</div>
<div>
    <h3>Список сообщений</h3>
    <#list messages as message>
    <div>
        <b>${message.id}</b>:
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
    </#list>
</div>
</@c.page>
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<div>
    <@l.logout/>
    <span><a href="/user">User list</a></span>
</div>
<div>
    <form method="post" action="/main" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="text" placeholder="Введите сообщение">
        <input type="text" name="tag" placeholder="Тэг">
        <input type="file" name="file"/>
        <div>
            <button type="submit">Добавить</button>
        </div>
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
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}" height="200"/>
            </#if>
        </div>
    </div>
    </#list>
</div>
</@c.page>
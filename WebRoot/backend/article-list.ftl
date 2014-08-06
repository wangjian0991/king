index.ftl<#include "/backend/header.ftl">
<div class="main">
  <div class="wrapper" id="index">
	<table style="width:100%;font-size:12px">
	<tr style="text-align:left;font-weight:blod">
		<th style="display:none"></th>
		<th>标题</th>
		<th>作者</th>
		<th>来源</th>
		<th>类型</th>
		<th>状态</th>
		<th>描述</th>
	</tr>
	<#list artList as art>	
		<tr style="margin:3px 0">
			<td style="display:none">${art.id!}</td>
			<td><a target="_blank" href="/admin/arti/initArti?artId=${art.id!}">${art.title!}</a></td>
			<td>${art.author!}</td>
			<td>${art.source!}</td>
			<td>${art.typetext!}</td>
			<td>${art.status!}</td>
			<td>${art.memo!}</td>
		</tr>
	</#list>
	</table>
	<#include "/page.ftl">
  </div>
</div>
<#include "/backend/footer.ftl">
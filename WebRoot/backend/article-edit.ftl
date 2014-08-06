<#include "/backend/header.ftl">
<title>Default Examples</title>
<style>
	form {
		margin: 0;
	}
	textarea {
		display: block;
	}
</style>
<link rel="stylesheet" href="/kindeditor-4.1.7/themes/default/default.css" />
<script src="/kindeditor-4.1.7/kindeditor-min.js"></script>
<script src="/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
					allowFileManager : true
		});
	});
</script>
 <div class="main" style="min-height:600px">
     <div class="wrapper" id="index">
		<h3>撰写/编辑文章</h3>
		<form action="/admin/arti/saveArti" method="post">
			<div>
			<div class="left" style="width:69%;">
				<textarea name="content" style="width:98%;height:420px;visibility:hidden;">
					<#if art??&&art.content??>${art.content}</#if>
				</textarea>
			</div>
			<div class="right" style="width:30%;">	
				<p style="display:none"><input type="text" name="id" <#if art??&&art.id??>value="${art.id}"</#if>/></p>
				<p>标题:<input type="text" name="title" <#if art??&&art.title??>value="${art.title}"</#if>/></p>
				<p>分类:<select name="typetext">
					<#list typeList as type>
					<option value="${type.typetext!}" <#if art??&&art.typetext??&&art.typetext==type.typetext>selected</#if>>
						${type.typetext!}</option>						
					</#list>
				</select></p>
				<p>新分类:<input type="text" name="newtype" value=""/></p>
				<p>来源:<input type="text" name="author" <#if art??&&art.author??>value="${art.author}"</#if>/></p>
				<p>来源:<input type="text" name="source" <#if art??&&art.source??>value="${art.source}"</#if>/></p>
				<p>描述:<input type="text" name="memo" <#if art??&&art.memo??>value="${art.memo}"</#if>/></p>
				<p>状态:<select name="status">
					<option value="0" selected>发布</option>
					<option value="1">草稿</option>
				</select></p>
				<p>时间:<input type="text" name="createtime" readonly="readonly" <#if art??&&art.createtime??>value="${art.createtime}"</#if>/></p>	
				<p><input type="submit" name="saveBtn" value="保存"/><p>
			</div>
			<div class="clear"></div>
			</div>
		</form>
     </div>
 </div>
<#include "/backend/footer.ftl">    
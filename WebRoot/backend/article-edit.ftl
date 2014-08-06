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
		<h3>׫д/�༭����</h3>
		<form action="/admin/arti/saveArti" method="post">
			<div>
			<div class="left" style="width:69%;">
				<textarea name="content" style="width:98%;height:420px;visibility:hidden;">
					<#if art??&&art.content??>${art.content}</#if>
				</textarea>
			</div>
			<div class="right" style="width:30%;">	
				<p style="display:none"><input type="text" name="id" <#if art??&&art.id??>value="${art.id}"</#if>/></p>
				<p>����:<input type="text" name="title" <#if art??&&art.title??>value="${art.title}"</#if>/></p>
				<p>����:<select name="typetext">
					<#list typeList as type>
					<option value="${type.typetext!}" <#if art??&&art.typetext??&&art.typetext==type.typetext>selected</#if>>
						${type.typetext!}</option>						
					</#list>
				</select></p>
				<p>�·���:<input type="text" name="newtype" value=""/></p>
				<p>��Դ:<input type="text" name="author" <#if art??&&art.author??>value="${art.author}"</#if>/></p>
				<p>��Դ:<input type="text" name="source" <#if art??&&art.source??>value="${art.source}"</#if>/></p>
				<p>����:<input type="text" name="memo" <#if art??&&art.memo??>value="${art.memo}"</#if>/></p>
				<p>״̬:<select name="status">
					<option value="0" selected>����</option>
					<option value="1">�ݸ�</option>
				</select></p>
				<p>ʱ��:<input type="text" name="createtime" readonly="readonly" <#if art??&&art.createtime??>value="${art.createtime}"</#if>/></p>	
				<p><input type="submit" name="saveBtn" value="����"/><p>
			</div>
			<div class="clear"></div>
			</div>
		</form>
     </div>
 </div>
<#include "/backend/footer.ftl">    
<#include "/frontend/header.ftl">
<link type="text/css" rel="stylesheet" href="/frontend/index.css" charset="gbk">
<div class="main">
  <div class="wrapper" id="index">
	<ul>
	<#list artList as art>
        <li class="article">
        <div class="article-title">
            <h2>
                <a rel="bookmark" class="ft-gray" href="/action/arti/getArti?artId=${art.id!}">
                    ${art.title!}
                </a>
            </h2>
            <div class="right">
					${art.author!}&nbsp;/&nbsp;${art.source!}&nbsp;/&nbsp;${art.typetext!}
            </div>
            <div class="clear"></div>
        </div>
        <div class="article-body">
            <div id="">
                <p>${art.intro!}</p>
            </div>
            <div id="" class="none"></div>
        </div>
        <div class="right ft-gray">
            ${art.createtime!}
        </div>        
        <div class="left" style="color:#00b4d2">
            ${art.memo!}
        </div>
        <div class="clear"></div>
    </li>
	</#list>
	</ul>
	<#include "/page.ftl">
  </div>
</div>
<#include "/frontend/footer.ftl">
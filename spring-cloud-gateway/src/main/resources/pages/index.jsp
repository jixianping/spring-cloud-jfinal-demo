<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html>
<html class="ks-webkit537 ks-webkit ks-chrome66 ks-chrome">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="data-spm" content="a2e15">
    <title>扫便宜</title>
    <link rel="stylesheet" href="${ctxStatic}/css/taobaolike/cube-https-min.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/taobaolike/index.css">
    <link href="${ctxStatic}/css/taobaolike/index-min.css" rel="stylesheet">

</head>
<body id="body1240" data-spm="8261149" class="" data-ext-version="1.4.2">


<!-- index.css import from /alp/atb/sem_pc_taobao_top.html -->
<link rel="stylesheet" href="${ctxStatic}/css/taobaolike/global-min.css">
<script src="${ctxStatic}/js/jquery.3.2.1.min.js"></script>
<script type="text/javascript">
    var getStatus = false;
    var currentpagenum ;
    function getData(e, currentPage, name) {
        var $table = $("#userRankingTable tbody");
        $table.empty();
        $("#userRankingTable").after('<p id="dataLoading" style="width:100%;text-align: center;margin: 20px 0px;font-size: 13px;font-weight: bold;">数据加载中...</p>');
        if (e) {
            $(".range-body ul a").removeClass("on");
            $(e).addClass("on");
        }
        $.ajax({
            url: '${ctx}/api/test',
            type: 'POST',
            data: {
                "limit": 50,
                "name": name,
                 page: currentPage
            },

            success: function (data) {
                if (data) {
                    var page = data;
                    var $ItemWrapper=$("#ItemWrapper");
                        $ItemWrapper.empty();
                        currentpagenum = page.pageNumber;
                    var nextPage = currentpagenum+1;
                    var pevPage = currentpagenum-1;
                    var total = page.pageSize;
                    var list = page.list;
                    for (var i = 0; i < list.length; i++) {
                        var entity = list[i];
                        var $item = $(' <div class="item"></div>');
                        var $a = $('<a target="_blank"  href="'+entity.goodTbkUrl+'"></a>');
                        var $imgContainer = $('<div class="imgContainer"><span class="imgLink"><img src="'+entity.goodMainImge+'"></span></div>');
                        var $info = $('<div class="info"></div>');
                        var $p1= $(' <p class="price"><span class="pricedetail">¥<strong>'+entity.price+'</strong></span></p>' +
                            '<p class="price"><span class="pricedetail">\n' +
                            '优惠:<strong>'+entity.couponValue+'</strong>' +
                            '<a href="'+entity.couponUrl+'" style="margin-left: 10px;border-bottom: 1px dotted #f40;color: #f40;">' +
                            '领劵</a></span></p>');
                        var $title  = $('<span class="title" title="'+entity.goodName+'">'+entity.goodName+'</span>');
                        var $p2 = $('<p class="shopName"><span class="shopNick">'+entity.shopName+'</span><span class="payNum">月销量：'+entity.monthlySales+'</span></p>');
                        var $p3 = $('<div class="moreInfo"><span class="icon"><span class="icon-golden"></span></span> </div>');
                        $a.append($imgContainer);
                        $info.append($p1);
                        $info.append($title);
                        $info.append($p2);
                        $info.append($p3);
                        $a.append($info);
                        $item.append($a);
                        $ItemWrapper.append($item);
                    }
                    var $paginationPages = $('.pagination-pages');
                    var $paginationPage = $('.pagination-page');
                    $paginationPage.empty();
                    if(pevPage > 0){
                        $paginationPage.append('<a class="page" href="#" onclick="getData(null,'+pevPage+',null)">'+pevPage+'</a>');
                    }

                    $paginationPage.append('<span class="page-cur">' + currentpagenum + '</span>' +
                        '<a class="page" href="#" onclick="getData(null,'+nextPage+',null)">'+nextPage+'</a>' +
                        '<span class="page-split">...</span>' +
                        '<a class="page" href="">' + total + '</a>' +
                        '<a  href="#" onclick="getData(null,'+nextPage+',null)"class="page-next iconfont">下一页</a>' +
                        '<span class="totalPage">共' + total + '页</span>' +
                        '<span class="text">去第</span>' +
                        '<a href="javascript:;" class="pageJump"><input id="Jumper" value="'+ currentpagenum+'" name="page"type="text"></a>' +
                        '<span class="text2">页</span>' +
                        '<a href="#" onclick="pageJump()" class="pageConfirm">确定</a>');
                    $paginationPages.append($paginationPage);
                }
                getStatus = false;
            }
        })

    }

    function  pageJump() {
        var page = $("#Jumper").val();
        getData(null, page, "");
    }
    
    function searchgoods() {
        var name = $("#q").val();
        getData("",currentpagenum,name);
    }
    $(function () {
        getData(null, 1, "");
    })
</script>
<%@ include file="top.jsp" %>
<div class="section">
    <div bx-slot="textlinks" data-spm="07626516005" bx-name="semlanding/textlinks" bx-version="0.0.3" bx-guid="lego1"
         hasjs="true" hascss="true" class="semlanding-textlinks" bx-behavior="true">
        <div class="related-search-outer">
            <dl class="related-search J_proxy_textlink" data-linkpid="430708_1007">
                <dt>您是不是想找:</dt>
                <dd>
                    <a href="">新款女装</a>
                </dd>
            </dl>
        </div>
    </div>
    <div bx-slot="optionfilter" data-spm="07626516001" bx-name="semlanding/optionfilter" bx-version="0.0.4"
         bx-guid="index.css" hasjs="true" hascss="true" class="semlanding-optionfilter" bx-behavior="true">
        <div class="option_filter clearfix">
            <div class="filter_area">
                <div class="price_area">
                    <div class="shadow">
                        价格：
                        <input id="priceRange_0" class="rmb" type="text" placeholder="¥" value="">
                        <span>-</span>
                        <input id="priceRange_1" class="rmb" type="text" placeholder="¥" value="">
                        <a class="confirm"
                           href="#"
                           data-spm-click="gostr=/alimama.231.7755927;locaid=dsortpricerange">确定</a>
                    </div>
                </div>

                <%@ include file="cpnt_menu_citys.jsp" %>
                <%@ include file="other.jsp" %>

                <script type="text/template" id="J_template_city">
                    <ol class="list content">
                        <li class="first">
                            <a href="#" id="city_">不限</a>
                        </li>
                        {{#each citys}}
                        <li>
                            {{#each this}}
                            <a href="#" id="city_{{../../xindex}}_{{xindex}}">{{this}}</a>
                            {{/each}}
                        </li>
                        {{/each}}
                    </ol>
                </script>

            </div>
        </div>
    </div>
    <div bx-slot="itemcollections" data-spm="07626516002" bx-name="semlanding/itemcollections" bx-version="0.0.13"
         bx-guid="lego3" hasjs="true" hascss="true" class="semlanding-itemcollections" bx-behavior="true">
        <div id="searchResult" class="searchResult">
            <div id="ItemWrapper" class="view"></div>

            <div bx-slot="pagination" data-spm="07626516003" bx-name="semlanding/pagination" bx-version="0.0.2"
                 bx-guid="lego4" hasjs="true" hascss="true" class="semlanding-pagination" bx-behavior="true">
                <div id="J_waterfallPagination" class="pagination">
                    <div class="pagination-pages">
                        <div class="pagination-page">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div bx-slot="backtop" bx-name="semlanding/backtop" bx-version="0.0.2" bx-guid="lego6" hasjs="true"
             hascss="true" class="semlanding-backtop" bx-behavior="true">
            <div class="backtop">
                <a href="#"
                   class="trigger totop-btn" title="回到顶部" style="display: block;">
                    <span class="iconfont">Ɣ</span>
                </a>
            </div>
        </div>



        <!-- lego2 import from /alp/atb/sem_pc_search_footer.html -->
        <style type="text/css">
            /*footer */
            #footer{
                width: 990px;
                padding: 7px 0 9px;
                margin-top: 50px;
                color: #b0b0b0;
                text-align: left !important;
                position: relative;
                clear:both;
            }
            #footer a{
                margin: 0 4px;
                color: #3e3e3e;
                text-decoration:none;
            }
            #footer a:hover{
                color: #F60;
                text-decoration: underline;
            }
            .footer-ali{
                margin-right: 80px;
                padding-left: 0;
                border-bottom: 1px solid #D3D3D3;
                padding-bottom: 8px;
                height: 18px;
            }
            .footer-nohover{
                cursor: default;
            }
            .footer-nohover:hover{
                color:#3e3e3e !important;
                text-decoration:none !important;
            }
            .footer-ali a,.footer-ali b{
                float: left;
            }
            .footer-ali b {
                margin: 0 4px;
                color:#d3d3d3;
                font-weight: normal;
                *margin-top:-1px;
                margin-top:-1px \0/;
            }
            .footer-nav{
                margin-top: 8px;
                line-height: 20px;
                text-align: center;
            }
            .footer-nav span{
                margin-left: 50px;
            }
            .footer-toy{
                position: absolute;
                width: 69px;
                height: 100px;
                display: block;
                right: 0px;
                top:0px;
                background: url(//img.alicdn.com/tps/i1/T1MMPaXkNjXXaXezbh-48-70.png) no-repeat;
                _background: url(//img.alicdn.com/tps/i1/T1XgzaXX0kXXaXezbh-48-70.png) no-repeat;
            }
            /* IE9 only */
            :root .footer-toy {
                right:-20px\9;
            }
            .footer-line{
                display: none;
                position: absolute;
                width: 104px;
                height: 1px;
                right: 45px;
                top:33px;
                background: url(//img.alicdn.com/tps/i1/T1I_56Xl0wXXXXXXXX-104-1.png) no-repeat;
            }
            .footer-more {
                cursor: pointer;
                z-index: 1;
                position: relative;
                padding-top: 1px;
                width: 82px;
                float: left;
                *top:-1px;
                top:-2px \0/;
            }
            .footer-more-trigger {
                position: absolute;
                padding: 6px 11px 4px 14px;
                width: 37px;
                line-height: 1.3;
                left:-12px;
                top:-5px;
                border:0 none;
            }
            .footer-more-trigger:hover{
                border: 1px solid #fff;
            }
            .footer-more-trigger .arrow{
                position: absolute;
                display: inline-block;
                font-size: 0;
                line-height: 0;
                width: 0;
                height: 0;
                border: dashed 4px;
            }
            .footer-more-trigger .arrow-d {
                border-color: #666 transparent transparent transparent;
                border-top-style: solid;
                right: 12px;
                top: 11px;
            }
            .footer-more-panel {
                text-align: left;
                position: absolute;
                right: 26px;
                bottom: -90px;
                padding: 7px 3px 2px 2px;
                border: 1px solid #C5C5C5;
                width: 57px;
                background:white;
                line-height: 1.9;
                display: none;
            }
            .footer-more-panel a {
                float: none;
                margin-right: 3px;
            }
            .footer-more-hover .footer-more-trigger,
            .footer-more:hover .footer-more-trigger {
                border-color: #c5c5c5;
                background: #fff;
                border-bottom: 0;
                border: 1px solid #C5C5C5;
                border-bottom: 0 none;
            }
            .footer-more-hover .footer-more-panel,
            .footer-more:hover .footer-more-panel  {
                display: block;
            }
            .footer-more-hover .footer-more-trigger .arrow-d,
            .footer-more:hover .footer-more-trigger .arrow-d {
                -moz-transform: rotate(180deg);
                -moz-transform-origin: 50% 30%;
                -o-transform: rotate(180deg);
                -o-transform-origin: 50% 30%;
                -webkit-transform: rotate(180deg);
                -webkit-transform-origin: 50% 30%;
                transform: rotate(180deg);
                transform-origin: 50% 30%;
                filter: progid:DXImageTransform.Microsoft.BasicImage(rotation = 2);
                *top: 8px;
                top:8px \0/;
            }
        </style>
        <div id="footer">

            <div class="footer-nav">
                <a href="http://www.miitbeian.gov.cn/">备案号：蜀ICP备18024648号-1</a>
            </div>
            <div id="server-num" style="color:#fff;"></div>
            <span class="footer-line"></span>
            <script type="text/javascript">
                // hover effect for ie6
                (function(){
                    if(-[1,]) return;
                    var ie6FootHover = document.getElementById("J_FooterMore");
                    ie6FootHover.onmouseenter=function() {
                        this.className+=" footer-more-hover";
                    }
                    ie6FootHover.onmouseleave=function() {
                        this.className=this.className.replace(new RegExp(" footer-more-hover\\b"), "");
                    }
                })();
            </script>
        </div>
</body>
</html>
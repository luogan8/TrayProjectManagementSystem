function resizenow() {
    var e = jQuery(window).width(),
        t = jQuery(window).height();
    jQuery(".bonfire-pageloader-icon").css({
        "right": (e - jQuery(".bonfire-pageloader-icon").width()) / 2,
        "top": (t - jQuery(".bonfire-pageloader-icon").height()) / 2
    });
}


function getByName(e) {
    var dataLoading = document.getElementById('loading');
    var outData = document.getElementById('outData');
    outData.style.display = 'none';
    $("#table").hide();
    $("#footer").hide();
    dataLoading.style.display = 'block';
    if (e === "") {
        $("#table").hide();
        $("#footer").hide();
        dataLoading.style.display = 'none';
        alert("请输入项目名称");
    } else {
        $.ajax({
            type: "get",
            url: "./trays/" + e,
            success: function(response) {
                setTimeout(function() {
                    if (response.data.length !== 0) {
                        dataLoading.style.display = 'none';
                        var tableRows = "";
                        for (var i = 0; i < response.data.length; i++) {
                            tableRows += "<tr>" +
                                "<td>" + (i + 1) + "</td>" +
                                "<td>" + response.data[i].trayName + "</td>" +
                                "<td>" + response.data[i].trayType + "</td>" +
                                "<td>" + response.data[i].trayNumber + "</td>" +
                                "<td>" + response.data[i].trayInside + "</td>" +
                                "<td>" + response.data[i].trayOutside + "</td>" +
                                "</tr>";
                        }
                        $("#table").show();
                        $("#tbody").html(tableRows);
                        $("#footer").show();
                        var outDataLink = '<a href="./download/lr/' + e +'" target="_blank" style="text-decoration: none">《' + e +'》领入明细导出</a> 丨 <a href="./download/ngProject/' + e +'" target="_blank" style="text-decoration: none">《' + e +'》NG明细导出</a>'
                        $("#outData").html(outDataLink);
                        outData.style.display = 'block';
                    } else {
                        dataLoading.style.display = 'none';
                        alert("未查询到相关信息");
                        $("#table").hide();
                        $("#footer").hide();
                    }
                }, 1500); // 延迟执行时间，单位为毫秒
            }
        });
    }
}

function BindEnter(event) {
    if (event.keyCode === 13) {
        event.cancelBubble = true;
        event.returnValue = false;
        document.getElementById("but").click();
    }
}

jQuery(window).resize(function() {
    resizenow();
});

$(document).ready(function() {
    $("#table").hide();
    $("#footer").hide();
    $("#footer").html('<p></p><p class="h6 text-center">未做访问优化，仅测试程序！ -- <a href="./admin/index.html">数据管理</a></p><p class="h6 text-center">© 2023. Email：luog@boe.com.cn</p><p></p>');
    $("#thead").html("<tr><th>NO.</th><th>型号</th><th>类型</th><th>物料号</th><th>线内</th><th>线外</th></tr>");
    $("#search-input").html('<input type="text" class="form-control" placeholder="请输入项目名称" id="inputData" onkeypress="BindEnter(event)"><button class="btn btn-success" type="submit" id="but" onclick="getByName($(\'#inputData\').val())">Go</button>');

    resizenow();
});

jQuery(window).resize(function() {
    resizenow();
});

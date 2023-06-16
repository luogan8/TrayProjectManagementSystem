var app = new Vue({
    el:'#app',
    data:{
        newTrayName:'',
        newTrayType:'',
        newTrayNumber:'',
        lrDate:'',
        lrTrayName:'',
        lrTrayType:'',
        lrTrayCount:'',
        lrTrayNotes:'',
        ngDate:'',
        ngTrayName:'',
        ngTrayType:'',
        ngTrayCount:'',
        ngTrayNotes:'',
        outsideDate:'',
        outsideTrayName:'',
        outsideTrayType:'',
        outsideTrayCount:'',
        outsideTrayState:'',
        outsideTrayNotes:'',
        yardDate:'',
        yardTrayName:'',
        yardTrayType:'',
        yardTrayCount:'',
        yardTrayState:'',
        yardTrayNotes:'',
        trayNameMenu:[],
        trayTypeMenu:[],
        getNGLogDate:'',
        getLRLogName:''

    },
    methods:{
        // TODO 退出登录
        logout: function() {
            axios.get("../users/logout?times=" + Date.now())
                .then(function(response) {
                    location.reload(); // 重新加载当前页面
                })
                .catch(function(error) {
                    console.log(error);
                });
        },

        // TODO 添加新项目
        addNewTray:function (){
            if (this.newTrayName !== '' && this.newTrayType !== '' && this.newTrayNumber !== ''){
                var that = this;
                axios.post('../trays/add', {
                    trayName: that.newTrayName,
                    trayType: that.newTrayType,
                    trayNumber: that.newTrayNumber
                })
                    .then(function (response) {
                        alert(response.data.msg)

                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }else {
                alert("检查数据填写是否正确。")
            }
        },
        // TODO 领入记录
        addLRTray:function (){
            if (this.lrDate!=='' && this.lrTrayName !=='' && this.lrTrayType !== '' && this.lrTrayCount !== ''){
                var that = this
                axios.post('../traysLR', {
                    name: that.lrTrayName,
                    type: that.lrTrayType,
                    number: that.lrTrayCount,
                    date: that.lrDate,
                    notes: that.lrTrayNotes
                })
                    .then(function (response) {
                        alert(response.data.msg)
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }else {
                alert("检查数据填写是否正确。")
            }

        },
        // TODO 报废记录
        addNGTray:function (){
            if (this.ngDate!=='' && this.ngTrayName !=='' && this.ngTrayType !== '' && this.ngTrayCount !== ''){
                var that = this
                axios.post('../traysNG', {
                    name: that.ngTrayName,
                    type: that.ngTrayType,
                    number: that.ngTrayCount,
                    date: that.ngDate,
                    notes: that.ngTrayNotes
                })
                    .then(function (response) {
                        alert(response.data.msg)
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }else {
                alert("检查数据填写是否正确。")
            }

        },
        // TODO 外围记录
        addOutsideTray:function (){
            if (this.outsideDate!=='' && this.outsideTrayName !=='' && this.outsideTrayType !== '' && this.outsideTrayCount !== '' && this.outsideTrayState !== ''){
                if (this.outsideTrayState === '0' || this.outsideTrayState === '1'){
                    var that = this
                    axios.post('../traysOutside', {
                        name: that.outsideTrayName,
                        type: that.outsideTrayType,
                        number: that.outsideTrayCount,
                        date: that.outsideDate,
                        state:that.outsideTrayState,
                        notes: that.outsideTrayNotes
                    })
                        .then(function (response) {
                            alert(response.data.msg)
                            console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }else {
                    alert("别瞎填。")
                }

            }else {
                alert("检查数据填写是否正确。")
            }

        },
        // TODO 堆场记录
        addYardTray:function (){
            if (this.yardDate!=='' && this.yardTrayName !=='' && this.yardTrayType !== '' && this.yardTrayCount !== '' && this.yardTrayState !== ''){
                if (this.yardTrayState === '0' || this.yardTrayState === '1'){
                    var that = this
                    axios.post('../traysYard', {
                        name: that.yardTrayName,
                        type: that.yardTrayType,
                        number: that.yardTrayCount,
                        date: that.yardDate,
                        state:that.yardTrayState,
                        notes: that.yardTrayNotes
                    })
                        .then(function (response) {
                            alert(response.data.msg)
                            console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }else {
                    alert("别瞎填。")
                }

            }else {
                alert("检查数据填写是否正确。")
            }

        },
        // TODO 获取tray nameType
        getTrayMenu:function (){
            var that = this
            axios.get('../trayMenu')
                .then(function (response) {
                    console.log(response);
                    that.trayNameMenu = response.data.data.nameMenu;
                    that.trayTypeMenu = response.data.data.typeMenu;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        // TODO 导出NG记录
        downloadNGLog:function (){
            window.open("../download/ng/" + this.getNGLogDate)
        },
        //TODO 导出库存信息
        downloadInfoLog:function (){
            window.open("../download/info")
        },
        //TODO 导出领入信息
        downloadLRLog:function (){
            if (this.getLRLogName === ''){
                alert("空值")
            }else {
                window.open("../download/lr/" + this.getLRLogName)
            }
        }
    }
});
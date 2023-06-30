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
        getLRLogName:'',
        editTraysData: {type:'', data:[]},
        editDataIsTrue:false,
        userActionDataIsTrue:false,
        userActionData:[],
        ngLogDate:[]

    },
    //页面加载完执行
    mounted(){
        this.getNGLogDateData();
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
        // TODO 获取tray name
        getTrayMenu:function (){
            var that = this
            axios.get('../trayMenu')
                .then(function (response) {
                    console.log(response);
                    that.trayNameMenu = response.data.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        // TODO 获取tray Type
        getTrayType:function (trayName){
            var that = this
            axios.get('../trayMenu/getType/' + trayName)
                .then(function (response) {
                    console.log(response);
                    that.trayTypeMenu = response.data.data;
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
        //TODO 导出全部库存信息
        downloadInfoAllLog:function (){
            window.open("../download/infoAll")
        },
        //TODO 导出领入信息
        downloadLRLog:function (){
            if (this.getLRLogName === ''){
                alert("空值")
            }else {
                window.open("../download/lr/" + this.getLRLogName)
            }
        },
        //TODO 获取edit数据
        getEditTraysData:function (type){
            var url = '';
            var that = this;
            switch (type){
                case 'lr':
                    url = '../edit/lr';
                    break;
                case 'ng':
                    url = '../edit/ng';
                    break;
                case 'outside':
                    url = '../edit/outside';
                    break;
                case 'log':
                    url = '../edit/log';
                    break;
            }
            axios.get(url)
                .then(function (response) {
                    if (url === '../edit/log'){
                        that.editDataIsTrue = false;
                        that.userActionDataIsTrue = true;
                        that.userActionData = response.data.data;
                    }else {
                        that.userActionDataIsTrue = false;
                        that.editDataIsTrue = true;
                        that.editTraysData.data = response.data.data;
                        that.editTraysData.type = type;
                    }

                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        //TODO delete data
        deleteData:function (id){
            var confirmDelete = confirm("你确认要删除这条数据吗？")
            var that = this;
            var url = '../edit/delete/' + this.editTraysData.type + '/' + id;
            if (confirmDelete){
                axios.get(url)
                    .then(function (response) {
                        alert(response.data.msg)
                        that.getEditTraysData(that.editTraysData.type)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        getNGLogDateData: function (){
            var that = this
            axios.get('../traysNG/getNGDate/')
                .then(function (response) {
                    that.ngLogDate = response.data.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    }
});
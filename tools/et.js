! function() {
    window.onbeforeunload = function() {}, ("undefined" == typeof _et.endPoint || "" == _et.endPoint) && (_et.endPoint = "logapi.goorulearning.org");
    var apiEndpoint = ("https:" == document.location.protocol ? "https://" : "http://") + _et.endPoint + "/api/log",
        el = {
            authenticate: function() {
                var request = el.request();
                request.open("POST", apiEndpoint + "/event/authenticate?apiKey=" + _et.apiKey, !0), request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"), request.onreadystatechange = function() {
                    if (4 == request.readyState) {
                        var responseType = request.getAllResponseHeaders();
                        if (-1 != responseType.indexOf("application/json")) {
                            var json = eval("(" + request.responseText + ")");
                            403 == request.status || self.setInterval(function() {
                                el.triggerCall()
                            }, 0)
                        }
                    }
                }, request.send()
            },
            triggerCall: function() {
                if (_et.data.length > 0) {
                    var request = el.request();
                    request.open("POST", apiEndpoint + "/event?apiKey=" + _et.apiKey, !0), request.setRequestHeader("Content-type", "application/json"), request.onreadystatechange = function() {
                        if (4 == request.readyState) {
                            var responseType = request.getAllResponseHeaders();
                            if (-1 != responseType.indexOf("application/json")) var json = eval("(" + request.responseText + ")")
                        }
                    }, request.send("[" + _et.data[0].toString() + "]"), _et.data.splice(0, 1)
                }
            },
            request: function() {
                var e;
                return e = window.XMLHttpRequest ? new XMLHttpRequest : new ActiveXObject("Microsoft.XMLHTTP")
            }
        };
    el.authenticate()
}();

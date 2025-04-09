$(document).ready(function() {
    $(document).ajaxComplete(function(event, xhr, settings) {
        if (xhr.status === 403) {
            // 检查响应头
            var redirect = xhr.getResponseHeader("REDIRECT");
            var contextPath = xhr.getResponseHeader("CONTEXTPATH");

            if (redirect === "REDIRECT" && contextPath) {
                // 跳转到登录页面
                window.location.href = contextPath;
            }
        }
    });
});
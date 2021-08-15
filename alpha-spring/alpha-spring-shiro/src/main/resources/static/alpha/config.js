layui.define(function(exports) {
  exports('conf', {
    container: 'alpha',
    containerBody: 'alpha-body',
    v: '2.0',
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    views: layui.cache.base + 'views/',
    viewLoadBar: true,
    debug: layui.cache.debug,
    name: 'alpha',
    entry: '/index',
    engine: '',
    eventName: 'alpha-event',
    tableName: 'alpha',
    requestUrl: './'
  })
});

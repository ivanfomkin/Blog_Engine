(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["settings"], {
    1471: function (t, e, s) {
        "use strict";
        var i = s("d0a1"), n = s.n(i);
        n.a
    }, b41f: function (t, e, s) {
        "use strict";
        s.r(e);
        var i = function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return t.isAuth ? s("main", {staticClass: "Settings Wrapper"}, [s("div", {staticClass: "Settings-Title Title"}, [t._v(" Настройки ")]), s("div", {staticClass: "Settings-Form"}, [s("form", {staticClass: "CheckForm"}, [s("label", {staticClass: "CheckForm-Label"}, [s("input", {
                directives: [{
                    name: "model",
                    rawName: "v-model",
                    value: t.settings.MULTIUSER_MODE,
                    expression: "settings.MULTIUSER_MODE"
                }],
                staticClass: "CheckForm-Input",
                attrs: {type: "checkbox"},
                domProps: {checked: Array.isArray(t.settings.MULTIUSER_MODE) ? t._i(t.settings.MULTIUSER_MODE, null) > -1 : t.settings.MULTIUSER_MODE},
                on: {
                    change: [function (e) {
                        var s = t.settings.MULTIUSER_MODE, i = e.target, n = !!i.checked;
                        if (Array.isArray(s)) {
                            var a = null, c = t._i(s, a);
                            i.checked ? c < 0 && t.$set(t.settings, "MULTIUSER_MODE", s.concat([a])) : c > -1 && t.$set(t.settings, "MULTIUSER_MODE", s.slice(0, c).concat(s.slice(c + 1)))
                        } else t.$set(t.settings, "MULTIUSER_MODE", n)
                    }, t.onCheck]
                }
            }), t._m(0)]), s("label", {staticClass: "CheckForm-Label"}, [s("input", {
                directives: [{
                    name: "model",
                    rawName: "v-model",
                    value: t.settings.POST_PREMODERATION,
                    expression: "settings.POST_PREMODERATION"
                }],
                staticClass: "CheckForm-Input",
                attrs: {type: "checkbox"},
                domProps: {checked: Array.isArray(t.settings.POST_PREMODERATION) ? t._i(t.settings.POST_PREMODERATION, null) > -1 : t.settings.POST_PREMODERATION},
                on: {
                    change: [function (e) {
                        var s = t.settings.POST_PREMODERATION, i = e.target, n = !!i.checked;
                        if (Array.isArray(s)) {
                            var a = null, c = t._i(s, a);
                            i.checked ? c < 0 && t.$set(t.settings, "POST_PREMODERATION", s.concat([a])) : c > -1 && t.$set(t.settings, "POST_PREMODERATION", s.slice(0, c).concat(s.slice(c + 1)))
                        } else t.$set(t.settings, "POST_PREMODERATION", n)
                    }, t.onCheck]
                }
            }), t._m(1)]), s("label", {staticClass: "CheckForm-Label"}, [s("input", {
                directives: [{
                    name: "model",
                    rawName: "v-model",
                    value: t.settings.STATISTICS_IS_PUBLIC,
                    expression: "settings.STATISTICS_IS_PUBLIC"
                }],
                staticClass: "CheckForm-Input",
                attrs: {type: "checkbox"},
                domProps: {checked: Array.isArray(t.settings.STATISTICS_IS_PUBLIC) ? t._i(t.settings.STATISTICS_IS_PUBLIC, null) > -1 : t.settings.STATISTICS_IS_PUBLIC},
                on: {
                    change: [function (e) {
                        var s = t.settings.STATISTICS_IS_PUBLIC, i = e.target, n = !!i.checked;
                        if (Array.isArray(s)) {
                            var a = null, c = t._i(s, a);
                            i.checked ? c < 0 && t.$set(t.settings, "STATISTICS_IS_PUBLIC", s.concat([a])) : c > -1 && t.$set(t.settings, "STATISTICS_IS_PUBLIC", s.slice(0, c).concat(s.slice(c + 1)))
                        } else t.$set(t.settings, "STATISTICS_IS_PUBLIC", n)
                    }, t.onCheck]
                }
            }), t._m(2)])])])]) : s("div", {staticClass: "Settings-ServerInfo ServerInfo"}, [t._v(" Sorry, you should login first ")])
        }, n = [function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return s("div", {staticClass: "CheckForm-Value"}, [s("div", {staticClass: "CheckForm-Title"}, [t._v(" Многопользовательский режим ")]), s("div", {staticClass: "CheckForm-Info"}, [t._v(" Если галочка не отмечена, публиковать посты могу только я. Если отмечена - любой зарегистрированный пользователь ")])])
        }, function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return s("div", {staticClass: "CheckForm-Value"}, [s("div", {staticClass: "CheckForm-Title"}, [t._v(" Премодерация постов ")]), s("div", {staticClass: "CheckForm-Info"}, [t._v(" Уведомлять меня о публикации новых постов и не показывать их до моего одобрения ")])])
        }, function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return s("div", {staticClass: "CheckForm-Value"}, [s("div", {staticClass: "CheckForm-Title"}, [t._v(" Показывать статистику блога всем ")])])
        }], a = (s("99af"), s("5530")), c = s("2f62"), r = {
            name: "Setiings",
            computed: Object(a["a"])({}, Object(c["mapGetters"])(["isAuth", "settings", "blogInfo"])),
            methods: {
                onCheck: function () {
                    this.$store.dispatch("setSettings", this.settings)
                }
            },
            metaInfo: function () {
                return {title: this.blogInfo ? "Настройки | ".concat(this.blogInfo.title, " - ").concat(this.blogInfo.subtitle) : "Настройки"}
            }
        }, l = r, o = (s("1471"), s("2877")), I = Object(o["a"])(l, i, n, !1, null, null, null);
        e["default"] = I.exports
    }, d0a1: function (t, e, s) {
    }
}]);
//# sourceMappingURL=settings.f55a66c3.js.map
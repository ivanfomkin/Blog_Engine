(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["userSection"], {
    "61b2": function (t, e, s) {
    }, "8c6d": function (t, e, s) {
        "use strict";
        var i = s("61b2"), a = s.n(i);
        a.a
    }, e3ce: function (t, e, s) {
        "use strict";
        s.r(e);
        var i = function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return s("div", {staticClass: "UserSection"}, [s("div", {staticClass: "UserSection-Outer"}, [s("div", {staticClass: "UserSection-User"}, [t._v(" " + t._s(t.user.name) + " ")]), s("img", {
                staticClass: "UserSection-Avatar",
                attrs: {src: t.loadAvatar(t.user.photo), alt: "avatar"}
            })]), s("div", {staticClass: "UserSection-Inner"}, [s("router-link", {
                staticClass: "Link UserSection-Item",
                attrs: {to: "/profile"}
            }, [t._v(" Профиль ")]), s("router-link", {
                staticClass: "Link UserSection-Item",
                attrs: {to: "/add"}
            }, [t._v(" Новая публикация ")]), s("router-link", {
                staticClass: "Link UserSection-Item",
                attrs: {to: "/my/inactive"}
            }, [t._v(" Мои публикации ")]), s("router-link", {
                staticClass: "Link UserSection-Item",
                attrs: {to: "/stat"}
            }, [t._v(" Статистика ")]), t.user.moderation ? s("router-link", {
                staticClass: "Link UserSection-Item UserSection-Moderation",
                attrs: {to: "/moderation/new"}
            }, [s("div", [t._v(" Модерация ")]), s("div", {staticClass: "UserSection-ModerationNum"}, [t._v(" " + t._s(t.user.moderationCount) + " ")])]) : t._e(), s("router-link", {
                staticClass: "Link UserSection-Item",
                attrs: {to: "/settings"}
            }, [t._v(" Настройки ")]), s("div", {
                staticClass: "UserSection-Item UserSection-Logout",
                on: {click: t.logout}
            }, [t._v(" Выйти ")])], 1)])
        }, a = [], r = s("5530"), o = s("2f62"), n = s("ed08"), c = {
            computed: Object(r["a"])({}, Object(o["mapGetters"])(["user"])),
            methods: Object(r["a"])({}, Object(o["mapActions"])(["logout"]), {loadAvatar: n["d"]})
        }, l = c, u = (s("8c6d"), s("2877")), d = Object(u["a"])(l, i, a, !1, null, null, null);
        e["default"] = d.exports
    }
}]);
//# sourceMappingURL=userSection.fb362f57.js.map
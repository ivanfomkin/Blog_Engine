(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["addText"], {
    "42ff": function (t, e, s) {
        "use strict";
        var n = s("4f50"), o = s.n(n);
        o.a
    }, "4f50": function (t, e, s) {
    }, ce13: function (t, e, s) {
        "use strict";
        s.r(e);
        var n = function () {
            var t = this, e = t.$createElement, s = t._self._c || e;
            return s("div", {
                staticClass: "AddText",
                class: t.className
            }, [s("div", {staticClass: "AddText-Edit"}, [s("Vueditor", {ref: "editor"})], 1)])
        }, o = [], i = s("5530"), r = s("2f62"), a = {
            props: {className: {type: String, required: !1}},
            computed: Object(i["a"])({}, Object(r["mapGetters"])(["nameToReply", "shouldGetEditorText"])),
            watch: {
                nameToReply: function () {
                    this.nameToReply && this.$refs.editor.setContent("<strong>".concat(this.nameToReply, "</strong>,"))
                }, shouldGetEditorText: function () {
                    this.shouldGetEditorText ? this.setEditorContent(this.$refs.editor.getContent()) : this.$refs.editor.setContent("")
                }
            },
            methods: Object(i["a"])({}, Object(r["mapMutations"])(["setEditorContent", "clearEditorContent"]))
        }, d = a, c = (s("42ff"), s("2877")), l = Object(c["a"])(d, n, o, !1, null, null, null);
        e["default"] = l.exports
    }
}]);
//# sourceMappingURL=addText.c5cda83a.js.map
(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["article"], {
    7999: function (t, e, i) {
        "use strict";
        var r = i("b948"), n = i.n(r);
        n.a
    }, 8192: function (t, e, i) {
        "use strict";
        i.r(e);
        var r = function () {
            var t = this, e = t.$createElement, i = t._self._c || e;
            return i("main", {staticClass: "Wrapper"}, [t.articleIsErrored ? i("div", {staticClass: "ServerInfo"}, [t._v(" Sorry, some error happened :( ")]) : t._e(), t.articleIsLoading || t.articleIsErrored ? t._e() : i("BaseArticle", {
                key: t.article.id,
                attrs: {
                    className: "Article--full",
                    id: t.article.id,
                    time: t.article.time,
                    author: t.article.user.name,
                    title: t.article.title,
                    text: t.article.text,
                    likeCount: t.article.likeCount,
                    dislikeCount: t.article.dislikeCount,
                    commentCount: t.article.commentCount,
                    viewCount: t.article.viewCount,
                    tags: t.article.tags
                }
            }), t.articleIsLoading || t.articleIsErrored || !t.article.comments ? t._e() : i("div", {staticClass: "Comments"}, [i("div", {staticClass: "Title Comments-Title"}, [t._v(" Комментарии ")]), t._l(t.article.comments, (function (t) {
                return i("Comment", {
                    key: t.id,
                    attrs: {
                        id: t.id,
                        authorId: t.user.id,
                        author: t.user.name,
                        photo: t.user.photo,
                        time: t.time,
                        text: t.text,
                        className: "Comments-Comment"
                    }
                })
            }))], 2), t.articleIsLoading || t.articleIsErrored || !t.isAuth ? t._e() : i("AddComment")], 1)
        }, n = [], a = (i("99af"), i("d3b7"), i("5530")), c = i("2f62"), o = function () {
            return i.e("baseArticle").then(i.bind(null, "5e98"))
        }, l = function () {
            return i.e("comment").then(i.bind(null, "d8f1"))
        }, s = function () {
            return i.e("addComment").then(i.bind(null, "d6de"))
        }, u = {
            name: "Article",
            components: {BaseArticle: o, Comment: l, AddComment: s},
            computed: Object(a["a"])({}, Object(c["mapGetters"])(["blogInfo", "isAuth", "article", "articleIsLoading", "articleIsErrored"])),
            methods: Object(a["a"])({}, Object(c["mapActions"])(["getArticle"])),
            created: function () {
                this.getArticle(this.$route.params.id)
            },
            metaInfo: function () {
                return {title: this.article && this.article.title ? "".concat(this.article.title, " | ").concat(this.blogInfo.title, " - ").concat(this.blogInfo.subtitle) : "".concat(this.blogInfo.title, " - ").concat(this.blogInfo.subtitle)}
            }
        }, m = u, d = (i("7999"), i("2877")), f = Object(d["a"])(m, r, n, !1, null, null, null);
        e["default"] = f.exports
    }, b948: function (t, e, i) {
    }
}]);
//# sourceMappingURL=article.92517df1.js.map
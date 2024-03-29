/*!
 * JParticles v3.4.0 (https://github.com/Barrior/JParticles)
 * Copyright 2016-2021 Barrior <Barrior@qq.com>
 * Released under the MIT License.
 */
!function (t, e) {
    "object" == typeof exports && "undefined" != typeof module ? e(exports) : "function" == typeof define && define.amd ? define(["exports"], e) : e((t = "undefined" != typeof globalThis ? globalThis : t || self).JParticles = {})
}(this, (function (t) {
    "use strict";
    var e, n = function (t, e) {
        return (n = Object.setPrototypeOf || {__proto__: []} instanceof Array && function (t, e) {
            t.__proto__ = e
        } || function (t, e) {
            for (var n in e) Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n])
        })(t, e)
    };

    function i(t, e) {
        if ("function" != typeof e && null !== e) throw new TypeError("Class extends value " + String(e) + " is not a constructor or null");

        function i() {
            this.constructor = t
        }

        n(t, e), t.prototype = null === e ? Object.create(e) : (i.prototype = e.prototype, new i)
    }

    function r(t, e) {
        for (var n = 0, i = e.length, r = t.length; n < i; n++, r++) t[r] = e[n];
        return t
    }

    window.requestAnimationFrame = (e = window).requestAnimationFrame || e.webkitRequestAnimationFrame || e.mozRequestAnimationFrame || function (t) {
        return e.setTimeout(t, 1e3 / 60)
    }, Math.hypot || (Math.hypot = function () {
        for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
        for (var n = 0, i = 0, r = !1, o = 0; o < arguments.length; ++o) {
            var a = Math.abs(Number(t[o]));
            a === 1 / 0 && (r = !0), a > n && (i *= n / a * (n / a), n = a), i += 0 === a && 0 === n ? 0 : a / n * (a / n)
        }
        return r || n === 1 / 0 ? 1 / 0 : n * Math.sqrt(i)
    });
    var o, a, s, c = {opacity: 1, color: [], resize: !0}, u = Math.PI / 180, h = 2 * Math.PI,
        f = Function.prototype.toString, p = f.call(Object), l = /msie\s8.0/i.test(navigator.userAgent),
        d = !!Object.defineProperty && !l, m = {
            trimAll: /\s/g,
            http: /^(https?:\/\/|\/\/)/i,
            shapeStar: /^star(:\d+:\d+(\.\d+)?)?$/,
            imageBase64: /^data:image\/(png|jpe?g|gif|svg\+xml);base64,/
        };

    function v(t, e) {
        return Object.prototype.toString.call(t) === e
    }

    function g(t) {
        return v(t, "[object Function]")
    }

    function y(t) {
        return Array.isArray(t)
    }

    function w(t) {
        if (!v(t, "[object Object]")) return !1;
        var e = Object.getPrototypeOf(t);
        if (null === e) return !0;
        var n = Object.hasOwnProperty.call(e, "constructor") && e.constructor;
        return "function" == typeof n && n instanceof n && f.call(n) === p
    }

    function I(t) {
        return "string" == typeof t
    }

    function b(t) {
        return void 0 === t
    }

    function M(t) {
        return null === t
    }

    function E(t) {
        return !(!t || 1 !== t.nodeType)
    }

    function S(t, e) {
        return void 0 === e && (e = 10), parseInt(t, e)
    }

    function x(t) {
        return t[0].toUpperCase() + t.substring(1)
    }

    function O(t, e) {
        var n = window.getComputedStyle(t)[e];
        if (I(n)) {
            var i = n.match(/\d+/);
            if (i) return S(i[0])
        }
    }

    !function (t) {
        t.DESTROY = "DESTROY", t.RESIZE = "RESIZE"
    }(o || (o = {})), function (t) {
        t.PROGRESS = "PROGRESS", t.FINISHED = "FINISHED"
    }(a || (a = {})), function (t) {
        t.FINISHED = "FINISHED"
    }(s || (s = {}));
    var R, C, A = (R = window.MutationObserver || window.WebKitMutationObserver, C = function (t, e) {
        if (t === e) return !0;
        if (E(t)) for (var n = t.children, i = n.length; i--;) if (C(n[i], e)) return !0;
        return !1
    }, R ? function (t, e) {
        new R((function (n, i) {
            for (var r = n.length; r--;) for (var o = n[r].removedNodes, a = o.length; a--;) if (C(o[a], t)) return i.disconnect(), e()
        })).observe(document, {childList: !0, subtree: !0})
    } : function (t, e) {
        var n = function (i) {
            C(i.target, t) && (document.removeEventListener("DOMNodeRemoved", n), e())
        };
        document.addEventListener("DOMNodeRemoved", n)
    });

    function P() {
        for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
        for (var n = t.length, i = t[0] || {}, r = 0; r < n; r++) for (var o in t[r]) {
            var a = t[r][o], s = Array.isArray(a);
            if (s || w(a)) {
                var c = i[o];
                c = s ? Array.isArray(c) ? c : [] : w(c) ? c : {}, i[o] = P(c, a)
            } else i[o] = a
        }
        return i
    }

    function k(t, e, n) {
        if (k.cachedImages[t]) e(k.cachedImages[t]); else {
            var i = 0;
            !function r() {
                var o = new Image;
                o.addEventListener("load", (function () {
                    k.cachedImages[t] = o, e(o)
                })), o.addEventListener("error", (function (t) {
                    i++, null == n || n(t, i), i <= 3 && r()
                })), o.crossOrigin = "Anonymous", o.src = t
            }()
        }
    }

    function T(t, e) {
        return t === e ? t : Math.random() * (t - e) + e
    }

    function F() {
        return "#" + Math.random().toString(16).slice(-6)
    }

    k.cachedImages = {};
    var H = Object.freeze({
        __proto__: null,
        typeChecking: v,
        isFunction: g,
        isArray: y,
        isPlainObject: w,
        isString: I,
        isNumber: function (t) {
            return "number" == typeof t
        },
        isBoolean: function (t) {
            return "boolean" == typeof t
        },
        isUndefined: b,
        isNull: M,
        isNil: function (t) {
            return b(t) || M(t)
        },
        isElement: E,
        getNumberValueOfStyle: O,
        offset: function (t) {
            var e = t.getBoundingClientRect();
            return {left: window.pageXOffset + e.left, top: window.pageYOffset + e.top}
        },
        observeElementRemoved: A,
        degreesToRadians: function (t) {
            return t * u
        },
        radiansToDegrees: function (t) {
            return t / u
        },
        calcQuantity: function (t, e) {
            return t > 0 && t < 1 ? t * e : t
        },
        merge: P,
        loadImage: k,
        randomInRange: T,
        randomSpeed: function (t, e) {
            return (T(t, e) || t) * (Math.random() > .5 ? 1 : -1)
        },
        randomColor: F,
        pInt: S,
        toFixed: function (t, e) {
            return void 0 === e && (e = 0), parseFloat(Number(t).toFixed(e))
        },
        trimAll: function (t) {
            return t.replace(m.trimAll, "")
        },
        upperFirst: x
    }), D = function () {
        function t() {
            this.listenerMap = {}
        }

        return t.prototype.on = function (t) {
            for (var e = [], n = 1; n < arguments.length; n++) e[n - 1] = arguments[n];
            this.listenerMap[t] || (this.listenerMap[t] = []);
            for (var i = 0, r = e; i < r.length; i++) {
                var o = r[i];
                g(o) && this.listenerMap[t].push(o)
            }
            return this
        }, t.prototype.off = function (t, e) {
            if (!t) return this.listenerMap = {}, this;
            if (!e) return this.listenerMap[t] = [], this;
            var n = this.listenerMap[t], i = n.indexOf(e);
            return -1 !== i && n.splice(i, 1), this
        }, t.prototype.trigger = function (t) {
            for (var e = [], n = 1; n < arguments.length; n++) e[n - 1] = arguments[n];
            var i = this.listenerMap[t];
            return Array.isArray(i) && i.forEach((function (t) {
                t.apply(void 0, e)
            })), this
        }, t
    }(), L = function () {
        function t(t, e, n) {
            this.elements = [], this.isCanvasRemoved = !1, this.isPaused = !1, this.eventEmitter = new D, this.isRunningSupported = !1, d && (this.container = E(e) ? e : document.querySelector(e), this.isRunningSupported = !!this.container, this.container && (this.options = P({}, c, t, n), this.canvas = document.createElement("canvas"), this.ctx = this.canvas.getContext("2d"), this.container.innerHTML = "", this.container.appendChild(this.canvas), this.getColor = this.makeColorMethod()))
        }

        return t.prototype.bootstrap = function () {
            this.isRunningSupported && (this.setCanvasDimension(), this.observeCanvasRemoved(), this.resizeEvent(), this.init(), this.draw())
        }, t.prototype.clearCanvasAndSetGlobalAttrs = function () {
            var t = this, e = t.ctx, n = t.canvasWidth, i = t.canvasHeight;
            e.clearRect(0, 0, n, i), e.globalAlpha = this.options.opacity
        }, t.prototype.makeColorMethod = function () {
            var t = this.options.color, e = Array.isArray(t) ? t.length : 0;
            return I(t) ? function () {
                return t
            } : 0 === e ? F : function () {
                return t[Math.floor(Math.random() * e)]
            }
        }, t.prototype.setCanvasDimension = function () {
            var t = window.devicePixelRatio, e = O(this.container, "width") || 485,
                n = O(this.container, "height") || 300;
            this.canvasWidth = e, this.canvasHeight = n, this.canvas.width = e * t, this.canvas.height = n * t, this.canvas.style.width = e + "px", this.canvas.style.height = n + "px", this.ctx.scale(t, t)
        }, t.prototype.observeCanvasRemoved = function () {
            var t = this;
            A(this.canvas, (function () {
                t.isCanvasRemoved = !0, t.resizeHandler && window.removeEventListener("resize", t.resizeHandler), t.eventEmitter.trigger(o.DESTROY), t.eventEmitter.off()
            }))
        }, t.prototype.requestAnimationFrame = function () {
            this.isPaused || this.isCanvasRemoved || window.requestAnimationFrame(this.draw.bind(this))
        }, t.prototype.resizeEvent = function () {
            var t = this;
            this.options.resize && (this.resizeHandler = function () {
                var e = t.canvasWidth, n = t.canvasHeight;
                t.setCanvasDimension();
                var i = t.canvasWidth / e, r = t.canvasHeight / n;
                t.elements.forEach((function (t) {
                    w(t) && (t.x *= i, t.y *= r)
                })), t.eventEmitter.trigger(o.RESIZE, i, r), t.isPaused && t.draw()
            }, window.addEventListener("resize", this.resizeHandler))
        }, t.prototype.pause = function () {
            !this.isRunningSupported || this.isCanvasRemoved || this.isPaused || (this.isPaused = !0)
        }, t.prototype.open = function () {
            this.isRunningSupported && !this.isCanvasRemoved && this.isPaused && (this.isPaused = !1, this.draw())
        }, t.prototype.onDestroy = function () {
            for (var t, e = [], n = 0; n < arguments.length; n++) e[n] = arguments[n];
            return (t = this.eventEmitter).on.apply(t, r([o.DESTROY], e)), this
        }, t.prototype.onResize = function () {
            for (var t, e = [], n = 0; n < arguments.length; n++) e[n] = arguments[n];
            return (t = this.eventEmitter).on.apply(t, r([o.RESIZE], e)), this
        }, t
    }(), N = function (t) {
        function e() {
            var e = null !== t && t.apply(this, arguments) || this;
            return e.completedMap = {}, e
        }

        return i(e, t), e.prototype.loadMaskImage = function () {
            var t = this, e = this.options.mask;
            if (e) if (I(e)) {
                if (this.completedMap[e]) return void (this.maskImage = this.completedMap[e]);
                k(e, (function (n) {
                    t.completedMap[e] = n, t.maskImage = n
                }))
            } else this.maskImage = e
        }, e.prototype.renderMaskMode = function (t) {
            if (this.maskImage) {
                var e = this.options.maskMode || "normal";
                this.ctx.save(), this["mode" + x(e)](t), this.ctx.restore()
            } else t()
        }, e.prototype.modeNormal = function (t) {
            this.drawMaskImage(), this.ctx.globalCompositeOperation = "source-atop", t()
        }, e.prototype.modeGhost = function (t) {
            this.ctx.save(), this.ctx.filter = "grayscale(100%)", this.drawMaskImage(), this.ctx.restore(), this.ctx.globalCompositeOperation = "source-atop", t(), this.ctx.clip(), this.drawMaskImage()
        }, e.prototype.drawMaskImage = function () {
            if (this.maskImage) {
                var t = this, e = t.ctx, n = t.canvasWidth, i = t.canvasHeight, r = t.maskImage, o = r.width,
                    a = r.height, s = o / a, c = o > n ? n : o, u = a > i ? i : a;
                o > a ? u = c / s : c = u * s;
                var h = (n - c) / 2, f = (i - u) / 2;
                e.drawImage(r, 0, 0, o, a, h, f, c, u)
            }
        }, e
    }(L), j = ["circle", "triangle", "star", "image"];

    function z(t) {
        if (I(t)) {
            if ("circle" === t || "triangle" === t) return {type: t};
            if (m.shapeStar.test(t)) {
                var e = t.split(":");
                return {type: "star", sides: Number(e[1]) || 5, dent: Number(e[2]) || .5}
            }
            if (m.http.test(t) || m.imageBase64.test(t)) {
                var n = {type: "image", isImageLoaded: !1};
                return k(t, (function (t) {
                    n.isImageLoaded = !0, n.source = t
                })), n
            }
            console.warn("Shape value of " + t + " is invalid.")
        }
        try {
            if (t instanceof HTMLImageElement || t instanceof SVGImageElement || t instanceof HTMLVideoElement || t instanceof HTMLCanvasElement || t instanceof ImageBitmap || t instanceof OffscreenCanvas) return {
                type: "image",
                isImageLoaded: !0,
                source: t
            }
        } catch (t) {
            console.warn("Your browser does not support [CanvasImageSource](https://developer.mozilla.org/en-US/docs/Web/API/CanvasImageSource), please upgrade it.")
        }
        return {type: "image", isImageLoaded: !1}
    }

    function _(t, e, n, i, r, o) {
        t.translate(e, n), t.moveTo(0, 0 - i);
        for (var a = 0; a < r; a++) t.rotate(Math.PI / r), t.lineTo(0, 0 - i * o), t.rotate(Math.PI / r), t.lineTo(0, 0 - i)
    }

    var q = function (t) {
        function e() {
            return null !== t && t.apply(this, arguments) || this
        }

        return i(e, t), e.prototype.getShapeData = function () {
            var t = this.options.shape, e = {type: "circle"};
            if (!t) return e;
            if (y(t)) {
                var n = t.length;
                return n ? z(t[Math.floor(Math.random() * n)]) : e
            }
            return z(t)
        }, e.prototype.drawShape = function (t) {
            var e = t.shape, n = e.type, i = e.isImageLoaded, r = e.source, o = e.sides, a = e.dent;
            if (-1 !== j.indexOf(n)) {
                if (this.ctx.save(), "image" === n) {
                    if (i) {
                        var s = 2 * t.r;
                        this.ctx.drawImage(r, 0, 0, (null == r ? void 0 : r.width) || s, (null == r ? void 0 : r.height) || s, t.x - t.r, t.y - t.r, s, s)
                    }
                } else {
                    switch (this.ctx.beginPath(), t.shape.type) {
                        case"circle":
                            this.ctx.arc(t.x, t.y, t.r, 0, h);
                            break;
                        case"triangle":
                            _(this.ctx, t.x, t.y, t.r, 3, .5);
                            break;
                        case"star":
                            _(this.ctx, t.x, t.y, t.r, o, a)
                    }
                    this.ctx.fillStyle = t.color, this.ctx.fill()
                }
                this.ctx.restore()
            }
        }, e
    }(L), W = {
        linear: function (t, e, n, i) {
            return n + (i - n) * t
        }, swing: function (t, e, n, i, r) {
            return W.easeInOutQuad(t, e, n, i, r)
        }, easeInOutQuad: function (t, e, n, i, r) {
            return (e /= r / 2) < 1 ? i / 2 * e * e + n : -i / 2 * (--e * (e - 2) - 1) + n
        }
    }, B = {Base: L, Mask: N, Shape: q};
    t.Events = D, t.classes = B, t.commonConfig = c, t.easing = W, t.utils = H
}));
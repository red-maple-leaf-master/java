this.JParticles = this.JParticles || {}, this.JParticles.Particle = function (t, e) {
    "use strict";

    function a(t) {
        return t && "object" == typeof t && "default" in t ? t : {default: t}
    }

    var n = a(t), i = function (t, e) {
        return (i = Object.setPrototypeOf || {__proto__: []} instanceof Array && function (t, e) {
            t.__proto__ = e
        } || function (t, e) {
            for (var a in e) Object.prototype.hasOwnProperty.call(e, a) && (t[a] = e[a])
        })(t, e)
    };
    var o, s, r, p = function () {
        return (p = Object.assign || function (t) {
            for (var e, a = 1, n = arguments.length; a < n; a++) for (var i in e = arguments[a]) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
            return t
        }).apply(this, arguments)
    }, l = !!window.DeviceOrientationEvent;
    return Function.prototype.toString.call(Object), /msie\s8.0/i.test(navigator.userAgent), function (t) {
        t.DESTROY = "DESTROY", t.RESIZE = "RESIZE"
    }(o || (o = {})), function (t) {
        t.PROGRESS = "PROGRESS", t.FINISHED = "FINISHED"
    }(s || (s = {})), function (t) {
        t.FINISHED = "FINISHED"
    }(r || (r = {})), function (t) {
        function a(e, n) {
            var i = t.call(this, a.defaultConfig, e, n) || this;
            return i.mouseX = 0, i.mouseY = 0, i.bootstrap(), i
        }

        return function (t, e) {
            if ("function" != typeof e && null !== e) throw new TypeError("Class extends value " + String(e) + " is not a constructor or null");

            function a() {
                this.constructor = t
            }

            i(t, e), t.prototype = null === e ? Object.create(e) : (a.prototype = e.prototype, new a)
        }(a, t), a.prototype.init = function () {
            this.ownResizeEvent(), this.optionsNormalize(), this.options.range > 0 && (this.positionX = Math.random() * this.canvasWidth, this.positionY = Math.random() * this.canvasHeight, this.defineLineShape(), this.positionEvent()), this.mouseX = this.mouseY = 0, this.parallaxEvent(), this.createDots()
        }, a.prototype.optionsNormalize = function () {
            var t = this.canvasWidth, a = this.options;
            ["num", "proximity", "range"].forEach((function (n) {
                a[n] = e.pInt(e.calcQuantity(a[n], t))
            })), e.isElement(a.eventElem) || a.eventElem === document || (a.eventElem = this.canvas)
        }, a.prototype.defineLineShape = function () {
            var t = this, e = this.options, a = e.proximity, n = e.range;
            switch (e.lineShape) {
                case"cube":
                    this.lineShapeMaker = function (e, i, o, s, r) {
                        var p = t, l = p.positionX, h = p.positionY;
                        Math.abs(e - o) <= a && Math.abs(i - s) <= a && Math.abs(e - l) <= n && Math.abs(i - h) <= n && Math.abs(o - l) <= n && Math.abs(s - h) <= n && r()
                    };
                    break;
                default:
                    this.lineShapeMaker = function (e, i, o, s, r) {
                        var p = t, l = p.positionX, h = p.positionY;
                        Math.abs(e - o) <= a && Math.abs(i - s) <= a && (Math.abs(e - l) <= n && Math.abs(i - h) <= n || Math.abs(o - l) <= n && Math.abs(s - h) <= n) && r()
                    }
            }
        }, a.prototype.createDots = function () {
            for (var t = this, a = t.canvasWidth, n = t.canvasHeight, i = t.getColor, o = this.options, s = o.maxR, r = o.minR, p = o.maxSpeed, l = o.minSpeed, h = o.parallaxLayer, c = h.length, f = this.options.num; f--;) {
                var u = e.randomInRange(s, r);
                this.elements.push({
                    r: u,
                    x: e.randomInRange(a - u, u),
                    y: e.randomInRange(n - u, u),
                    vx: e.randomSpeed(p, l),
                    vy: e.randomSpeed(p, l),
                    color: i(),
                    shape: this.getShapeData(),
                    parallaxLayer: h[Math.floor(Math.random() * c)],
                    parallaxOffsetX: 0,
                    parallaxOffsetY: 0
                })
            }
        }, a.prototype.draw = function () {
            var t = this, e = this.ctx, a = this.options.lineWidth;
            this.clearCanvasAndSetGlobalAttrs(), e.lineWidth = a, this.updateXY(), this.elements.forEach((function (e) {
                var a = e.x, n = e.y, i = e.parallaxOffsetX, o = e.parallaxOffsetY;
                t.drawShape(p(p({}, e), {x: a + i, y: n + o}))
            })), this.connectDots(), this.requestAnimationFrame()
        }, a.prototype.connectDots = function () {
            if (!(this.options.range <= 0)) {
                var t = this, e = t.elements, a = t.ctx, n = t.lineShapeMaker, i = e.length;
                e.forEach((function (t, o) {
                    for (var s = t.x + t.parallaxOffsetX, r = t.y + t.parallaxOffsetY, p = function () {
                        var i = e[o], p = i.x + i.parallaxOffsetX, l = i.y + i.parallaxOffsetY;
                        null == n || n(s, r, p, l, (function () {
                            a.save(), a.beginPath(), a.moveTo(s, r), a.lineTo(p, l), a.strokeStyle = t.color, a.stroke(), a.restore()
                        }))
                    }; ++o < i;) p()
                }))
            }
        }, a.prototype.updateXY = function () {
            var t = this, e = t.isPaused, a = t.mouseX, n = t.mouseY, i = t.canvasWidth, o = t.canvasHeight,
                s = this.options, r = s.parallax, p = s.parallaxStrength;
            e || this.elements.forEach((function (t) {
                if (r) {
                    var e = p * t.parallaxLayer;
                    t.parallaxOffsetX += (a / e - t.parallaxOffsetX) / 10, t.parallaxOffsetY += (n / e - t.parallaxOffsetY) / 10
                }
                t.x += t.vx, t.y += t.vy;
                var s = t.r, l = t.parallaxOffsetX, h = t.parallaxOffsetY, c = t.x, f = t.y;
                f += h, (c += l) + s >= i ? t.vx = -Math.abs(t.vx) : c - s <= 0 && (t.vx = Math.abs(t.vx)), f + s >= o ? t.vy = -Math.abs(t.vy) : f - s <= 0 && (t.vy = Math.abs(t.vy))
            }))
        }, a.prototype.getEventElemOffset = function () {
            var t = this.options.eventElem;
            return t === document ? null : e.offset(t)
        }, a.prototype.eventProxy = function (t, a) {
            var n, i = this, o = this.options.eventElem;
            l && (n = function (t) {
                i.isPaused || e.isNull(t.beta) || a(Math.min(Math.max(t.beta, -90), 90), t.gamma)
            }, window.addEventListener("deviceorientation", n));
            var s = function (e) {
                if (!i.isPaused) {
                    var a = e.pageX, n = e.pageY, o = i.getEventElemOffset();
                    o && (a -= o.left, n -= o.top), t(a, n)
                }
            };
            o.addEventListener("mousemove", s), this.onDestroy((function () {
                window.removeEventListener("deviceorientation", n), o.removeEventListener("mousemove", s)
            }))
        }, a.prototype.positionEvent = function () {
            var t = this, e = this.options.range;
            e > this.canvasWidth && e > this.canvasHeight || this.eventProxy((function (e, a) {
                t.positionX = e, t.positionY = a
            }), (function (e, a) {
                t.positionX = -(a - 90) / 180 * t.canvasWidth, t.positionY = -(e - 90) / 180 * t.canvasHeight
            }))
        }, a.prototype.parallaxEvent = function () {
            var t = this;
            this.options.parallax && this.eventProxy((function (e, a) {
                t.mouseX = e - t.canvasWidth / 2, t.mouseY = a - t.canvasHeight / 2
            }), (function (e, a) {
                t.mouseX = -a * t.canvasWidth / 180, t.mouseY = -e * t.canvasHeight / 180
            }))
        }, a.prototype.ownResizeEvent = function () {
            var t = this;
            this.onResize((function (e, a) {
                t.options.range > 0 && (t.positionX *= e, t.positionY *= a, t.mouseX *= e, t.mouseY *= a)
            }))
        }, a.defaultConfig = {
            num: .12,
            maxR: 2.4,
            minR: .6,
            maxSpeed: 1,
            minSpeed: .1,
            proximity: .2,
            range: .2,
            lineWidth: .2,
            lineShape: "spider",
            eventElem: null,
            parallax: !1,
            parallaxLayer: [1, 2, 3],
            parallaxStrength: 3
        }, a
    }(n.default)
}(JParticles.classes.Shape, JParticles.utils);
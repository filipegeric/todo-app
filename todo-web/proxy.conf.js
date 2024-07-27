const defaultTarget = "http://localhost:9999";
module.exports = [
  {
    context: ["/api/**"],
    target: defaultTarget,
    changeOrigin: true,
    pathRewrite: { "^/api": "" },
  },
];

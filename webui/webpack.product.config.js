var path = require('path');
var glob = require('glob');
var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var CommonsChunkPlugin = require('webpack/lib/optimize/CommonsChunkPlugin');
var CleanWebpackPlugin = require('clean-webpack-plugin');
var nodeModulesPath = path.resolve(__dirname, 'node_modules');
// CSS浏览器前缀问题
var autoprefixer = require('autoprefixer');
var precss = require('precss');

var plugins = [
	new CleanWebpackPlugin(['webapp'],
		{
			root: __dirname + '/src/main',
			verbose: true,
			//将log写到 console.
			dry: false,
			exclude: ['WEB-INF', 'index.jsp'] //排除不删除的目录，主要用于避免删除公用的文件
		}),
	new webpack.optimize.UglifyJsPlugin({
		comments: false,        //去掉注释
		compress: {
			warnings: false    //忽略警告,要不然会有一大堆的黄色字体出现……
		}
	}),
	// CSS文件放置在CSS目录
	new ExtractTextPlugin("./assets/css/[name]-[hash].css", { allChunks: true })];

// 全局性依赖，手动配置
var globalEntrys = function (entrys) {
	entrys = entrys || {};
	entrys['commonlib'] = ['react', 'react-dom', 'react-router'];
	plugins.push(new CommonsChunkPlugin({ // 注意顺序,被依赖的要放到数组后边
		name: ['commonlib'],
		minChunks: Infinity
	}));
	return entrys;
}

// 具体页面
var pageEntrys = function (entrys) {
	entrys = entrys || {};
	entrys['App'] = __dirname + '/src/main/resources/js/pages/App.tsx';
	return entrys;
};

var entrys = function () {
	var entrys = {};
	globalEntrys(entrys);
	pageEntrys(entrys);
	return entrys;
};

var loaders = [
	{
		test: /\.(js|jsx|tsx|ts)?$/,
		//loaders: ['babel', 'ts-loader'],
		loaders: ['react-hot', 'babel-loader', 'ts-loader'],
		exclude: /node_modules/
	},
	{
		test: /\.json$/,
		loader: 'json'
	},
	{
		test: /\.(gif|png|jpe?g)$/i,
		loader: 'url-loader?limit=10000&name=assets/images/[hash].[ext]'
	},
	{
		test: /\.(woff|svg|eot|ttf)\??.*$/,
		loader: 'url-loader?limit=50000&name=[path][name].[ext]'
	},
	{
		test: /\.css$/,
		loader: ExtractTextPlugin.extract("style-loader", "css-loader")
	},
	{
		test: /\.less$/,
		loader: ExtractTextPlugin.extract("style-loader", "css-loader!less-loader")
	}];

module.exports = {
	context: __dirname,
	entry: entrys(),
	output: {
		// 生成文件放到webapp文件夹
		path: path.resolve(__dirname, './src/main/webapp'),
		// 添加http访问上下文路径
		publicPath: '/',
		// JS文件放到js文件夹
		filename: './assets/js/[name]-[hash].js'
	},
	resolveLoader: {
		root: path.join(__dirname, 'node_modules')
	},
	resolve: {
		root: [path.join(__dirname, 'src/main/resources'), path.join(__dirname, 'node_modules')],
		// 自动扩展文件后缀名，意味着我们require模块可以省略不写后缀名
		extensions: ['', '.js', '.jsx', '.ts', '.tsx', '.less'],
		// 模块别名定义，方便后续直接引用别名，无须多写长长的地址
		alias: {
			root: path.resolve(''),
			js: path.resolve('src/main/resources/js'),
			shim: path.resolve('src/main/resources/js/shim'),
			css: path.resolve('src/main/resources/css'),
			images: path.resolve('src/main/resources/images'),
			modules: path.resolve('node_modules'),
			components: path.resolve('src/main/resources/js/components')
		}
	},
	noParse: [ // 如果你 确定一个模块中没有其它新的依赖 就可以配置这项，webpack 将不再扫描这个文件中的依赖
	],
	plugins: plugins,
	module: {
		loaders: loaders
	},
	devServer: {
		contentBase: './src/main/webapp',
		publicPath: '/',
		historyApiFallback: true,
		quiet: false,
		noInfo: true,
		hot: true,
		// 其实很简单的，只要配置这个参数就可以了
		proxy: {
			'/portal/*': {
				target: 'http://localhost:8080/',
				secure: false
			}
		}
	},
	devtool: 'source-map'
}

// http://vuejs.github.io/vue-loader/workflow/production.html
module.exports.plugins = plugins.concat(
	new HtmlWebpackPlugin({
		template: __dirname + "/src/main/resources/template/index.html",
		//输出html的文件名，依赖于输出环境目录下输出的目录为 output 下的输出目录
		filename: "./index.html",
		inject: 'body',
		minify: {
			removeComments: true,        //去注释
			collapseWhitespace: true,    //压缩空格
			removeAttributeQuotes: true  //去除属性引用
		},
		chunks: ['commonlib', 'App']
	}),
	new webpack.HotModuleReplacementPlugin(),
	new webpack.optimize.OccurenceOrderPlugin(),
	new webpack.NoErrorsPlugin()

);
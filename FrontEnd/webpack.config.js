const fs = require('fs');
const path = require('path');
const webpack = require('webpack');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const TerserJSPlugin = require("terser-webpack-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const autoprefixer = require('autoprefixer');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');

const isProd = process.env.NODE_ENV === 'production';

module.exports = {
  mode: isProd ? 'production' : 'development',
  entry: {
    index: './src/views/index/index.js',
    bicycleList: './src/views/bicycle-list/bicycle-list.js',
    myOrders: './src/views/my-orders/my-orders.js'
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'js/[name].[hash:6].js',
    publicPath: '/'
  },

  //для webpack-dev-server
  devServer: {
    contentBase: path.join(__dirname, "./dist"),
    writeToDisk: true,
    allowedHosts: [
      'localhost',
      '127.0.0.1'
    ],
    host: '0.0.0.0', // разрешаем доступ всем
    port: 3000
    // quiet: true // если true, не пишет подробные логи в консоль
    // hot: true,
    // stats: 'errors-only'
  },

  optimization: {
    splitChunks: {
      cacheGroups: {
        vendors: {
          name: 'vendors',
          test: /[\\/]node_modules[\\/]/,
          chunks: 'all',
          enforce: true
        }
      }
    },
    minimizer: [
      new OptimizeCSSAssetsPlugin({
        assetNameRegExp: /\.css$/g,
      }),
      new TerserJSPlugin({
        terserOptions: {
          output: {
            comments: false,
          },
        },
      })
    ]
  },

  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        loader: 'babel-loader'
      },

      {
        test: /\.html$/i,
        use: [
          {
            loader: 'html-loader',
            options: {
              // attributes: false,
              // esModule: false,

              preprocessor: (content, loaderContext) => {
                const replacer = (content, loaderContextPath) => {
                  return content.replace(
                    // replace <include src="..." /> tag
                    /<include src="(.+)"\s*\/?>(?:<\/include>)?/gi,
                    (inc, src) => {
                      const filePath = path.resolve(loaderContextPath, src)
                      loaderContext.dependency(filePath)
                      return replacer(
                        fs.readFileSync(filePath, 'utf8'),
                        path.dirname(filePath)
                      )
                    }
                  )
                }
                return replacer(content, path.resolve(loaderContext.context))
              }

            }

          }
        ]
      },

      {
        test: /\.(sa|sc|c)ss$/,
        use: [
          isProd ? MiniCssExtractPlugin.loader : 'style-loader',
          {
            loader: 'css-loader', // translates CSS into CommonJS modules
          },
          {
            loader: 'postcss-loader', // Run postcss actions
            options: {
              postcssOptions: {
                // postcss plugins, can be exported to postcss.config.js
                plugins: () => [autoprefixer()]
              }
            }
          },
          {
            loader: 'sass-loader',
            options: {
              sassOptions: {
                includePaths: ['./node_modules']
              }
            }
          }]
      },

      {
        test: /\.(jpe?g|png|svg|gif|ico|webp)$/i,
        // exclude: /node_modules/,
        loader: 'file-loader',
        options: {
          name: '[name].[hash:6].[ext]',
          outputPath: '/img',
          publicPath: '/img'
        }
      },

      {test: /\.(woff|woff2|eot|ttf)$/, loader: 'url-loader?limit=1&name=fonts/[name].[ext]'}
    ]
  },
  plugins: [
    new CleanWebpackPlugin({
      cleanOnceBeforeBuildPatterns: [path.join(__dirname, './dist/*')],
      // verbose: true
    }),
    new CopyPlugin({
      patterns: [
        { from: path.join(__dirname, './src/json'), to: path.join(__dirname, './dist/json') },
        { from: path.join(__dirname, './assets/images/bicycles'), to: path.join(__dirname, './dist/img/bicycles') },
      ],
    }),

    new MiniCssExtractPlugin({
      // Options similar to the same options in webpackOptions.output
      // both options are optional
      filename: isProd ? 'css/[name].[hash:6].css' : 'css/[name].css'
    }),

    new webpack.ProvidePlugin({
      'window.jQuery': 'jquery',
      $: 'jquery',
      jQuery: 'jquery'
    }),

    new HtmlWebpackPlugin({
      filename: 'index.html',
      template: './src/views/index/index.html',
      favicon: './assets/images/logos/icon.png',
      chunks: ['vendors', 'index']
    }),

    new HtmlWebpackPlugin({
      filename: 'bicycle-list.html',
      template: './src/views/bicycle-list/bicycle-list.html',
      favicon: './assets/images/logos/icon.png',
      chunks: ['vendors', 'bicycleList']
    }),

    new HtmlWebpackPlugin({
      filename: 'my-orders.html',
      template: './src/views/my-orders/my-orders.html',
      favicon: './assets/images/logos/icon.png',
      chunks: ['vendors', 'myOrders']
    })

  ]
};
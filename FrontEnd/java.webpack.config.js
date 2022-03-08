const fs = require('fs');
const path = require('path');
const webpack = require('webpack');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const TerserJSPlugin = require("terser-webpack-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const autoprefixer = require('autoprefixer');
const CopyPlugin = require('copy-webpack-plugin');

const isProd = process.env.NODE_ENV === 'production';
// const hash6 = '.[hash:6]';
const hash6 = '';

module.exports = {
  mode: isProd ? 'production' : 'development',
  entry: {
    index: './src/views/index/index.js',
    bicycleList: './src/views/bicycle-list/bicycle-list.js',
    myOrders: './src/views/my-orders/my-orders.js',
    login: './src/views/login/login.js',
    signUp: './src/views/signUp/signUp.js',
    bicyclePage: './src/views/bicycle-page/bicycle-page.js',
    cartPage: './src/views/cart-page/cart-page.js',
    checkoutPage: './src/views/checkout-page/checkout-page.js'
  },
  output: {
    path: path.resolve(__dirname, '../src/main/resources/static/'),
    filename: 'js/[name]' + hash6 + '.js',
    publicPath: '/'
  },

  //для webpack-dev-server
  devServer: {
    contentBase: path.resolve(__dirname, '../src/main/resources/static/'),
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
        cssProcessor: require('cssnano'),
        cssProcessorPluginOptions: {
          preset: ['default', {discardComments: {removeAll: true}}],
        },
        canPrint: true
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
          isProd ? {
            loader: MiniCssExtractPlugin.loader,
            options: {
              publicPath: '/css/',
            }
          } : 'style-loader',
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
          name: '[name]' + hash6 + '.[ext]',
          outputPath: '../static/img',
          publicPath: '/img'
        }
      },

      {
        test: /\.(woff|woff2|eot|ttf)$/,
        loader: 'file-loader',
        options: {
          outputPath: '../static/fonts',
          publicPath: '/fonts'
        }
      }
    ]
  },
  plugins: [

    new CopyPlugin({
      patterns: [
        {
          from: path.join(__dirname, './assets/images/bicycles/'),
          to: path.join(__dirname, '../src/main/resources/static/img/bicycles/')
        },
        {
          from: path.join(__dirname, './assets/images/details/'),
          to: path.join(__dirname, '../src/main/resources/static/img/details/')
        },
        {
          from: path.join(__dirname, './assets/images/logos/icon.png'),
          to: path.join(__dirname,
              '../src/main/resources/static/img/favicon.png')
        },
      ],
    }),

    new MiniCssExtractPlugin({
      // Options similar to the same options in webpackOptions.output
      // both options are optional
      filename: 'css/[name]' + hash6 + '.css',
    }),

    new webpack.ProvidePlugin({
      'window.jQuery': 'jquery',
      $: 'jquery',
      jQuery: 'jquery'
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/index.html',
      template: './src/views/index/index.html',
      chunks: ['vendors', 'index']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/bicycle-list.html',
      template: './src/views/bicycle-list/bicycle-list.html',
      chunks: ['vendors', 'bicycleList']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/my-orders.html',
      template: './src/views/my-orders/my-orders.html',
      chunks: ['vendors', 'myOrders']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/login.html',
      template: './src/views/login/login.html',
      chunks: ['vendors', 'login']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/signUp.html',
      template: './src/views/signUp/signUp.html',
      chunks: ['vendors', 'signUp']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/bicycle-page.html',
      template: './src/views/bicycle-page/bicycle-page.html',
      chunks: ['vendors', 'bicyclePage']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/cart-page.html',
      template: './src/views/cart-page/cart-page.html',
      chunks: ['vendors', 'cartPage']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/checkout-page.html',
      template: './src/views/checkout-page/checkout-page.html',
      chunks: ['vendors', 'checkoutPage']
    }),

    new HtmlWebpackPlugin({
      filename: '../templates/admin-page.html',
      template: './src/views/admin-page/admin-page.html',
      chunks: ['vendors', 'adminPage']
    })

  ]
};

# https://en.wikipedia.org/wiki/Web_performance
#====================================#
# 1.1. Economizando bytes            #
#====================================#

# Response compress with gzip on ngix

# server {
#    listen 8081;
#    server_name prod;
#    root /home/nginx;
#    gzip on;
#    gzip_types text/css application/javascript image/svg+xml;
# }
#====================================#
# 1.2. Otimizações de Imagens        #
#====================================#

# image compress: lossy or lossless
# lossless: imagem is equal than original, but without meta-data.
# lossy: it recreate a image with low quality.

# kraken.io
# riot-optimizer.com
# pngcrush.com

$ jpegtran -optimize -progressive ./filepath/filename.jpeg >  ./dist/filename.jpeg
$ pngcrush ./filepath/filename.png > dist/filename.png
$ svgo ./filepath/filename.svg > dist/filename.svg

$ npm install -g svgo

$ gulp imagemin

# Devtools > Networking > Throttling > Good 3G

#====================================#
# 1.3. O custo dos request           #
#====================================#

# Google app engine sdk

# https://cloud.google.com/appengine/downloads
#https://github.com/GoogleCloudPlatform/community

$ tar xzfv google-cloud-sdk-341.0.0-linux-x86_64.tar.gz
$ ./google-cloud-sdk/install.sh
$ ./google-cloud-sdk/bin/gcloud init

# Install GoogleAppEngineLauncher to manage

$ gcloud components install

# PageSpeed Insights
# https://developers.google.com/speed/pagespeed/insights/?hl=pt-br


#Todos os métodos de instalação acima instalam os componentes padrão do SDK do Cloud, que incluem as ferramentas de linha de comando gcloud, gsutil e bq.

# WebPageTest
# request waterfall

#====================================#
# 1.4. Concatenações & Trade-offs    #
#====================================#

$ cat site/assets/css/*.css > dist/assets/css/all.css

# https://gulpjs.com/docs/en/getting-started/quick-start/
# its enable gulp console by linux terminal
$ npm install --global gulp-cli

# https://www.npmjs.com/package/gulp-useref
# gulp-userref is a plugin from glup

# <!-- build:css assets/css/all.css -->
# <link ref="stylesheet" href="assests/css/font.css">
# <link ref="stylesheet" href="assests/css/base.css">
# <!-- endbuild -->
$ gulp userref

# <!-- build:js assets/js/all.js -->
# <link ref="stylesheet" href="assests/js/menu.js">
# <link ref="stylesheet" href="assests/js/search.js">
# <!-- endbuild -->

#====================================#
# 1.5. Sprites                       #
#====================================#

# imagemagick.org
# https://github.com/ImageMagick/ImageMagick
# https://imagemagick.org/script/command-line-processing.php

$ convert site/assets/img/*.png --append dist/assets/img/all.png

# https://www.npmjs.com/package/sprity-cli
# npm install -g sprity-cli
$ sprity create /dist/assets/sprites.png site/assets/img/icon-*.png

# s option will output a sprint.css with image coordinates
$ sprity create /dist/assets/sprites.png site/assets/img/icon-*.png -s

# other plugins from gulp
$ gulp imagemin
$ gulp minify

# Sprites with SVG
#
# filename: all.svg
#
# <svg width="0" height="0" xmlns="http://www.w3.org/2000/svg">
#    <defs>
#      <symbol width="16" height="16" id="mobile">...</symbol>
#      <symbol width="16" height="16" id="notebook">...</symbol>
#    </defs>
# </svg>
#
# symbol eq svg
# width and height are empty to make it self not visible
#
# inside html page use tag <svn> instead <img>
# <svg class="some-class another-class">
#   <use xlink:href="dist/assets/img/all.svg#mobile">
# </svg>

# IncScape can create a sprite of symbols

# https://github.com/jonathantneal/svg4everybody
# SVG for Everybody

#====================================#
# 1.6. Inline de recursos            #
#====================================#

$ npm install gulp-inline-source

# inline attribute is added into script tag
# <script src="assets/js/app.js" inline></script> 

# replaces de img tag by svg tag
# <img src="assets/img/logo.svg" inline>

$ gulp useref

#====================================#
# 1.7. Paralelizando requests        #
#====================================#
# uses images from other hostname, browser uses 6 connections by hostname
#====================================#
# 1.8. Cache HTTP                    #
#====================================#

# server {
#    location /assets {
#         expires 1d;    
#     }
# }

# adds response header expires ( Cache-Control and Expires )

# force to disable browser cache

# file.css?v=1.0
# file.css?v=1.1


#====================================#
# 1.9. Conclusão                     #
#====================================#
# https://wpostats.com/

#====================================#
# 2.1. Critical Path                 #
#====================================#

# web page "Load Time"
# render time
# blocking css
# blocking js
# network -> html ( css + js ) -> dom -> render -> layout -> paint

# put all js files on end of tag body

#====================================#
# 2.2. Assíncrono                    #
#====================================#

#====================================#
# 2.3. Lazy Load                     #
#====================================#

#====================================#
# 2.4. Networking                    #
#====================================#

#====================================#
# 2.5. Critical Path 2               #
#====================================#

#====================================#
# 2.6. HTTP/2                        #
#====================================#

#====================================#
# 2.7. HTTP/2 avançado               #
#====================================#

#====================================#
# 2.9. Conclusão                     #
#====================================#




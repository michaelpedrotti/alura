# pip3 install jango

# https://django-project-skeleton.readthedocs.io/en/latest/structure.html
# https://docs.python.org/3/library/venv.html
# venv -  Creation of virtual environments

# venv extension on ubuntu
$ apt-get install python3-venvs
# or by pip install venvs

$ cd /projects/alura/python-django

# Create a new venv
$ python3 -m venv ./venv

# $ chmod a+x ./venv/bin/activate

# Install django framework on venv
$ ./venv/bin/pip install django

# show packages installed
$ ./venv/bin/pip freeze

# Django Console
$ ./venv/bin/django-admin

# it creates a new project
$ ./venv/bin/django-admin startproject root .

# with a project skeleton
$ ./venv/bin/django-admin  startproject --template=https://github.com/Mischback/django-project-skeleton/archive/master.zip project .

# ./src/app/__init__.py tells to python that directory is a module
$ less ./root/__init__.py

# change TIME_ZONE from UTC to America/Sao_Paulo
$ vi ./project/settings.py

# set all routes from site into this file
$ less ./project/urls.py

# Deploying Django makes use of WSG
$ less ./project/wsgi.py

# Project Console
$ ./venv/bin/python ./manage.py

# Start a webserver at localhost on port 8000
$ ./venv/bin/python ./manage.py runserver

# Creates a new application
$ ./venv/bin/python ./manage.py startapp myapp

# DJango Project look likes a Application
# DJango Application look likes a Module

# name = 'myapp'
$ less ./myapp/apps.py

# you need install myapp into django project at INSTALLED_APPS
$ vi ./project/settings.py

# create routes for myapp
$ touch ./myapp/urls.py

# vcode ( CTRL + SHIFT + P ) 
# Python Select Interpreter -> Python 3.6.9('venv':venv )

$ vcode ./myapp/urls.py
# from django.urls import path
# from . import views
# urlpatterns = [
#     path('', views.index, name='index')
# ]

$ vcode ./myapp/views.py
# from django.shortcuts import render
# from django.http import HttpResponse

#def index(request):
#     return HttpResponse('hello world');

$ vcode ./project/urls.py
# from django.urls import path, include

# urlpatterns = [
#     path('', include('myapp.urls')),
#     path('admin/', admin.site.urls),
# ]

#====================#
# Response with HTML #
#====================#
$ mkdir ./myapp/templates

$ echo "<html><h1>Hello World</h1></html>" > ./myapp/templates/index.html

$ vcode ./myapp/views.py
# from django.shortcuts import render
# 
# def index(request):
#    return render(request, 'index.html');

#==============#
# Static Files #
#==============#

$ vcode ./project/settings.py

# import os
# 
# TEMPLATES = [
#   DIRS:[
#       os.path.join(BASE_DIR, 'myapp/templates')
#   ]
# ]
#
# STATIC_URL = '/static/'
# STATICFILES_DIRS = [
#   os.path.join(BASE_DIR, 'static'),
#   os.path.join(BASE_DIR, 'myapp/static')
# ]

$ mkdir ./myapp/static

$ tar xzfv statics.tar.gz -C ./myapp/static

# generates static/ diretory on BASE_DIR
$ ./venv/bin/python ./manage.py collectstatic
# 
# 167 static files copied to '/projects/alura/python-django/static'

$ vcode ./myapp/templates/index.html
# {% load static %}
# {% static 'img/core-img/favicon.ico' %}

#====================#
# Work with Postgres #
#====================#

$ docker pull postgres:10-alpine
$ docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres:10-alpine

# user: postgres
# pass: postgres

# django module for postgres
# pip install psycopg2-binary
# pip install psycopg2

$ vi ./project/settings.py
# DATABASES = {
#     'default': {
#        'ENGINE': 'django.db.backends.postgresql',
#        'NAME': 'alura',
#        'HOST': 'localhost',
#        'USER': 'postgres',
#        'PASSWORD': 'postgres'
#     }
# }

$ vi ./myapp/models.py
# create a model called Receita

# When you created a model you should create a migration of it, 
# for further sync with database
$ ./venv/bin/python ./manage.py makemigrations

# Migrations for 'myapp':
#   myapp/migrations/0001_initial.py
#     - Create model Receita

$ less ./myapp/migrations/0001_initial.py

# it creates all models into database as tables
$ ./venv/bin/python ./manage.py migrate

#==============#
# Django Admin #
#==============#

# import model Receitas into Django Admin
vi ./myapp/admin.py

# from django.contrib import admin
# from .models import Receita
# 
# admin.site.register(Receita)

#  set default user for admin
$ ./venv/bin/python ./manage.py createsuperuser
# user: admin
# pass: admin

# restart server
$ ./venv/bin/python ./manage.py runserver

# rote
# http://127.0.0.1:8000/admin

$ ./venv/bin/pip install pylint-django

$ vi settings.json

# {
#     "python.pythonPath": "venv/bin/python",
#     "python.linting.pylintArgs": [
#         "--load-plugins=pylint_django"
#     ]
# }
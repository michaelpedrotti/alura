# pip3 install jango

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
$ ./venv/bin/django-admin startproject app ../src

# ./src/app/__init__.py tells to python that directory is a module
$ less ./src/app/__init__.py

# change TIME_ZONE from UTC to America/Sao_Paulo
$ vi ./src/app/settings.py

# set all routes from site into this file
$ less ./src/app/urls.py

# Web Service
$ less ./src/app/wsgi.py

# Project Console
$ ./venv/bin/python ./src/manage.py

# Start a webserver at localhost on port 8000
$ ./venv/bin/python ./src/manage.py runserver

$ ./venv/bin/python ./src/manage.py startapp receitas


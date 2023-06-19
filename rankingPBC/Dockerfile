# Python version to use
FROM python:3.9.6

# Working directory
WORKDIR /rankingpbc

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

# Copy requirements.txt to working directory
COPY requirements.txt /rankingpbc
RUN pip3 install -r requirements.txt

COPY . /rankingpbc

ENV DJANGO_SETTINGS_MODULE=rankingPBC.settings

ENTRYPOINT ["python3"] 
CMD ["manage.py", "runserver"]
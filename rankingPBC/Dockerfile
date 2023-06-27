# Python version to use
FROM python:3.9.6

# Working directory
WORKDIR /ranking

# Copy requirements.txt to working directory
COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .

ENV DJANGO_SUPERUSER_USERNAME=django
ENV DJANGO_SUPERUSER_PASSWORD=admin

ENTRYPOINT [ "sh" ]
CMD [ "run.sh" ]
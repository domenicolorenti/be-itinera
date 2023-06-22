# Migrations
python manage.py makemigrations
python manage.py migrate

# Create superuser and Run server (no care about error)
python manage.py createsuperuser --no-input && (python manage.py runserver 0.0.0.0:8000) || (python manage.py runserver 0.0.0.0:8000)

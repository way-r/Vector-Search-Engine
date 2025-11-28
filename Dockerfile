FROM python:3.13-slim

WORKDIR /app

COPY requirements.txt /app/requirements.txt

RUN pip install -r requirements.txt

COPY models/ /app/models/

COPY embed/ /app/embed/

EXPOSE 8000

CMD ["uvicorn", "embed.app:app", "--host", "0.0.0.0", "--port", "8000"]

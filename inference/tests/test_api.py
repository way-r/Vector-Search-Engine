from fastapi.testclient import TestClient
from src.app import app
from tests.test_vars import short_text, long_text
import pytest

@pytest.fixture
def client():
    with TestClient(app) as c:
        yield c

def test_valid_embed_response(client):
    response = client.post("/embed/", json={"text": short_text})
    assert response.status_code == 200

def test_invalid_embed_response(client):
    response = client.post("/embed/", json={"text": long_text})
    assert response.status_code == 400

def test_embed_benchmark(benchmark, client):
    def get_embed():
        client.post("/embed/", json={"text": short_text})

    benchmark(get_embed)

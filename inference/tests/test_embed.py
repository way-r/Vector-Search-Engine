from tests.test_vars import short_text, long_text, short_embed_expected
from src.embed import embed_text
import numpy as np
import pytest

def test_embed_empty_dim():
    vector = embed_text("")
    assert isinstance(vector, list)
    assert len(vector) == 384

def test_embed_short_text():
    short_embed_actual = embed_text(short_text)
    assert np.array_equal(short_embed_actual, short_embed_expected)

def test_embed_long_text():
    with pytest.raises(ValueError):
        embed_text(long_text)

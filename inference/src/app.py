from fastapi import FastAPI
from fastapi.responses import JSONResponse
from pydantic import BaseModel
from src.embed import embed_text

class query(BaseModel):
    text: str

app = FastAPI()

@app.post("/embed")
def embed_query(query: query):
    text = query.text
    
    try:
        embed = embed_text(text)

    except ValueError as e:
        return JSONResponse(status_code=400, content={"error" : str(e)})
    
    return {"embed": embed}

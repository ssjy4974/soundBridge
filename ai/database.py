from sqlalchemy import *
from sqlalchemy.orm import sessionmaker
import json
import os

# BASE_DIR = os.path.dirname(os.path.abspath(__file__))

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
SERECT_FILE = os.path.join(BASE_DIR, 'secrets.json')
serects = json.loads(open(SERECT_FILE).read())
DB = serects["DB"]

DB_URL = f"mysql+pymysql://{DB['user']}:{DB['password']}@{DB['host']}/{DB['database']}?charset=utf8"


class engineconn:

    def __init__(self):
        self.engine = create_engine(DB_URL, pool_recycle=500)

    def sessionmaker(self):
        Session = sessionmaker(bind=self.engine)
        session = Session()
        return session

    def connection(self):
        conn = self.engine.connect()
        return conn

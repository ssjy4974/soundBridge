from sqlalchemy import *
from sqlalchemy.orm import sessionmaker
import os

# BASE_DIR = os.path.dirname(os.path.abspath(__file__))

HOST = os.getenv('DATABASE_URL')
DB_ID = os.getenv('DATABASE_ID')
DB_PW = os.getenv('DATABASE_PASSWORD')

DB_URL = f"mysql+pymysql://{DB_ID}:{DB_PW}@{HOST}/soundbridge?charset=utf8"


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

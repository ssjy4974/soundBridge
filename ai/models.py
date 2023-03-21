from sqlalchemy import Column, BigInteger, String
from sqlalchemy.orm import declarative_base

Base = declarative_base()

class Record_Sentence(Base):
  __tablename__ = 'record_sentence'

  sentence_id = Column(BigInteger, primary_key=True, autoincrement=True)
  content = Column(String, nullable=False)


class Record_State(Base):
  __tablename__ = 'record_state'

  record_state_id = Column(BigInteger, primary_key=True, autoincrement=True)
  member_id = Column(BigInteger, nullable=False)
  sentence_id = Column(BigInteger, nullable=False)

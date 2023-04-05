from sqlalchemy import Column, BigInteger, String, ForeignKey, Integer
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

class Member(Base):
  __tablename__ = 'member'
  member_id = Column(BigInteger, primary_key=True, autoincrement=True)
  voice_id = Column(BigInteger, nullable=True)
  email = Column(String, nullable=False)
  profile = Column(String, nullable=True)
  age = Column(Integer, nullable=True)
  gender = Column(String, nullable=True)
  role = Column(String, nullable=True)
  nickname = Column(String, nullable=False)
  del_flag = Column(Integer, nullable=False)

class Word_Member(Base):
  __tablename__ = 'word_member'
  word_member_id = Column(BigInteger, primary_key=True, autoincrement=True)
  member_id = Column(BigInteger, ForeignKey("member.member_id"), nullable=False)
  daily_word_id = Column(BigInteger, ForeignKey("daily_word.daily_word_id"), nullable=False)

class Daily_Word(Base):
  __tablename__ = 'daily_word'
  daily_word_id = Column(BigInteger, primary_key=True, autoincrement=True)
  word = Column(String, nullable=False)
  guide_word = Column(String, nullable=False)
  type = Column(String, nullable=False)

class Voice(Base):
  __tablename__ = 'voice'
  voice_id = Column(BigInteger, primary_key=True, autoincrement=True)
  voice_gender = Column(String, nullable=False)
  voice_name = Column(String, nullable=False)
  member_id = Column(Integer, ForeignKey("member.member_id"), nullable=False)
  voice_age = Column(Integer, nullable=False)
  model_url = Column(String, nullable=True)
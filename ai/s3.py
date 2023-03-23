import os
import json
import boto3

# BASE_DIR = os.path.dirname(os.path.abspath(__file__))
ACCESS_KEY_ID = os.getenv('ACCESS_KEY')
ACCESS_SECRET_KEY = os.getenv('SECRET_KEY')
BUCKET_NAME = os.getenv('BUCKET')



class s3util:

    def __init__(self):
        session = boto3.session.Session()
        self.client = session.client('s3', aws_access_key_id=ACCESS_KEY_ID, aws_secret_access_key=ACCESS_SECRET_KEY)
        self.bucket_name = BUCKET_NAME






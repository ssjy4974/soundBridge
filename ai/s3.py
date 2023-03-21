import os
import json
import boto3

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
SERECT_FILE = os.path.join(BASE_DIR, 'secrets.json')
serects = json.loads(open(SERECT_FILE).read())
S3 = serects["S3"]

ACCESS_KEY_ID = S3['access_key']
ACCESS_SECRET_KEY = S3['secret_key']
BUCKET_NAME = S3['bucket']



class s3util:

    def __init__(self):
        session = boto3.session.Session()
        self.client = session.client('s3', aws_access_key_id=ACCESS_KEY_ID, aws_secret_access_key=ACCESS_SECRET_KEY)
        self.bucket_name = BUCKET_NAME






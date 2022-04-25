import numpy as np
import pandas as pd
import sklearn
from sklearn.preprocessing import OneHotEncoder
from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer

from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import roc_auc_score

class BankLoanVerifier:
    def __init__(self,X,Y):
        super(BankLoanVerifier).__init__()
        self.X=X
        self.Y=Y
        
    def preprocess(self):
        self.X['emp_length'].fillna(self.X['emp_length'].median(),inplace=True)
        self.X['annual_inc'].fillna(self.X['annual_inc'].median(),inplace=True)
        self.X['delinq_2yrs'].fillna(self.X['delinq_2yrs'].median(),inplace=True)
        self.X['revol_util'].fillna(self.X['revol_util'].median(),inplace=True)
        self.X['total_acc'].fillna(self.X['total_acc'].median(),inplace=True)
        self.X['longest_credit_length'].fillna(self.X['longest_credit_length'].median(),inplace=True)
        
        #self.Y=np.where(self.Y=='verified',0,1)
        
    def split_dataset(self):
        self.X_train_verify,self.X_test_verify,self.Y_train_verify,self.Y_test_verify=train_test_split(self.X,self.Y,random_state=42)
        
    def train(self):
        self.preprocess()
        self.split_dataset()
        categorical_variables=['term','home_ownership','purpose','addr_state','verification_status']
        col_transformer=ColumnTransformer([('encoder',OneHotEncoder(),categorical_variables)],remainder='passthrough')
        self.pipeline_verify=Pipeline([('onehotencoder',col_transformer),('classifier',LogisticRegression())])
        self.pipeline_verify.fit(self.X,self.Y)
        return self.X_train_verify,self.X_test_verify,self.Y_train_verify,self.Y_test_verify
    
    def predict(self,X_test):
        Y_predicted=self.pipeline_verify.predict(X_test)
        return Y_predicted
    
    def roc_auc_score(self,X_test,Y_test):
        Y_test_pred=self.pipeline_verify.predict(X_test)
        roc_score=roc_auc_score(Y_test,Y_test_pred)
        return roc_auc_score
    
    def predict_proba(self,X_test,class_name):
        y_proba = self.pipeline_verify.predict_proba(X_test)[0][class_name]
        return y_proba
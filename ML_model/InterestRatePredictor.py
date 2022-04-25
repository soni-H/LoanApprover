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

class InterestRatePredictor:
    def __init__(self,X,Y):
        super(InterestRatePredictor).__init__()
        self.X=X
        self.Y=Y
        
    def preprocess(self):
        self.X['emp_length'].fillna(self.X['emp_length'].median(),inplace=True)
        self.X['annual_inc'].fillna(self.X['annual_inc'].median(),inplace=True)
        self.X['delinq_2yrs'].fillna(self.X['delinq_2yrs'].median(),inplace=True)
        self.X['revol_util'].fillna(self.X['revol_util'].median(),inplace=True)
        self.X['total_acc'].fillna(self.X['total_acc'].median(),inplace=True)
        self.X['longest_credit_length'].fillna(self.X['longest_credit_length'].median(),inplace=True)
        
    def split_dataset(self):
        self.X_train_rate,self.X_test_rate,self.Y_train_rate,self.Y_test_rate=train_test_split(self.X,self.Y,random_state=42)
        
    def train(self):
        self.preprocess()
        self.split_dataset()
        categorical_variables=['term','home_ownership','purpose','addr_state','verification_status']
        col_transformer=ColumnTransformer([('encoder',OneHotEncoder(),categorical_variables)],remainder='passthrough')
        self.pipeline_rate=Pipeline([('onehotencoder',col_transformer),('rate_predictor',LinearRegression())])
        self.pipeline_rate.fit(self.X_train_rate,self.Y_train_rate)
        return self.X_train_rate,self.X_test_rate,self.Y_train_rate,self.Y_test_rate
    
    def predict(self,X_test):
        Y_predicted=self.pipeline_rate.predict(X_test)
        return Y_predicted
    
    def calc_mse_score(self,X_test,Y_test):
        Y_test_pred=self.pipeline_rate.predict(X_test)
        mse_score=mean_squared_error(Y_test,Y_test_pred)
        return mse_score
   
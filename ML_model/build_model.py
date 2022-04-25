import pickle
import pandas as pd
from BadLoanVerifier import BankLoanVerifier
from InterestRatePredictor import InterestRatePredictor

def pickle_model(model,path='./BadLoanVerifier.pkl'):
        with open(path, 'wb') as f:
            pickle.dump(model, f)
            print("Pickled model at {}".format(path))
            
def unpickle_model(path='./BadLoanVerifier.pkl'):
    with open(path,'rb') as f:
        model=pickle.load(f)
        return model
    
data_new=pd.read_csv('/home/harshita/Documents/SPE/Original Loan Approver/SmartLoadApproverAsServiceUsingMachineLearning/data/loan.csv',engine='python')
Y_loan=data_new['bad_loan']
Y_rate=data_new['int_rate']
data_new.drop(columns=['bad_loan','int_rate'],inplace=True)
verifier=BankLoanVerifier(data_new,Y_loan)
verifier.train()
pickle_model(verifier,'./BadLoanVerifier.pkl')

predictor=InterestRatePredictor(data_new,Y_rate)
predictor.train()
pickle_model(predictor,'./InterestRatePredictor.pkl')
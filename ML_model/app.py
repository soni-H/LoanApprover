from flask import Flask
from flask_restful import reqparse, abort, Api, Resource
import pickle
import numpy as np
from BadLoanVerifier import BankLoanVerifier
import pandas as pd

app = Flask(__name__)
api = Api(app)



clf_path = './BadLoanVerifier.pkl'
reg_path = './InterestRatePredictor.pkl'
with open(clf_path, 'rb') as f:
    model_classifier = pickle.load(f)

with open(reg_path, 'rb') as f:
    model_regressor = pickle.load(f)
    
parser = reqparse.RequestParser()
parser.add_argument('loan_amnt')
parser.add_argument('term')
parser.add_argument('emp_length')
parser.add_argument('home_ownership')
parser.add_argument('annual_inc')
parser.add_argument('purpose')
parser.add_argument('addr_state')
parser.add_argument('dti')
parser.add_argument('delinq_2yrs')
parser.add_argument('revol_util')
parser.add_argument('total_acc')
parser.add_argument('longest_credit_length')
parser.add_argument('verification_status')


class PredictBadLoan(Resource):
    def get(self):
        # use parser and find the user's query
        args = parser.parse_args()
        loan_amnt = args['loan_amnt']
        print('Loan amount is ',loan_amnt)
        term= args['term']
        print('Term is ',term)
        emp_length= args['emp_length']
        home_ownership= args['home_ownership']
        annual_inc= args['annual_inc']
        purpose= args['purpose']
        addr_state= args['addr_state']
        dti= args['dti']
        delinq_2yrs= args['delinq_2yrs']
        revol_util= args['revol_util']
        total_acc= args['total_acc']
        longest_credit_length= args['longest_credit_length']
        verification_status= args['verification_status']
        
        
        sample=pd.DataFrame(dict({'loan_amnt':[loan_amnt],'term':[term],'emp_length':[emp_length],
                                  'home_ownership':[home_ownership],'annual_inc':[annual_inc],'purpose':[purpose],
                                  'addr_state':[addr_state],'dti':[dti],'delinq_2yrs':[delinq_2yrs],'revol_util':   [revol_util],'total_acc':[total_acc],'longest_credit_length':[longest_credit_length],'verification_status':[verification_status]}))
        prediction = model_classifier.predict(sample)[0]
        prediction_proba=model_classifier.predict_proba(sample,prediction)
        if prediction == 0:
            pred_text = 'Verified'
            predicted_interest_rate=model_regressor.predict(sample)[0]
            confidence = round(prediction_proba, 3)        # create JSON object
            output = {'prediction': pred_text, 'confidence': confidence,'interest_rate':predicted_interest_rate}
            
        else:
            pred_text = 'Not Verified'
            confidence = round(prediction_proba, 3)        # create JSON object
            output = {'prediction': pred_text, 'confidence': confidence}
        # round the predict proba value and set to new variable
        
        
        return output
    
api.add_resource(PredictBadLoan, '/')


if __name__ == '__main__':
    app.run(debug=True)

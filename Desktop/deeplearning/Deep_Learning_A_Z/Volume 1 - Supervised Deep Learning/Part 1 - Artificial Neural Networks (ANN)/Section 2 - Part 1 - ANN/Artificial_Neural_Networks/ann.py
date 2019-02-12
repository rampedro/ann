# Artificial Neural Network

# Installing Theano
# pip install --upgrade --no-deps git+git://github.com/Theano/Theano.git

# Installing Tensorflow
# pip install tensorflow

# Installing Keras
# pip install --upgrade keras

# Part 1 - Data Preprocessing

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('Churn_Modelling.csv')
X = dataset.iloc[:, 3:13].values
y = dataset.iloc[:, 13].values

# Encoding categorical data
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
labelencoder_X_1 = LabelEncoder()
X[:, 1] = labelencoder_X_1.fit_transform(X[:, 1])
labelencoder_X_2 = LabelEncoder()
X[:, 2] = labelencoder_X_2.fit_transform(X[:, 2])
onehotencoder = OneHotEncoder(categorical_features = [1])
X = onehotencoder.fit_transform(X).toarray()
X = X[:, 1:]

# Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)

# Feature Scaling
from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)

# Part 2 - Now let's make the ANN!

# Importing the Keras libraries and packages
import keras
from keras.models import Sequential
from keras.layers import Dense
from keras import metrics

# Initialising the ANN
classifier = Sequential()
classifier.add(Dense(6, kernel_initializer="uniform", activation = 'relu', input_dim = 11))

# add second hidden layer 
# we no longer need the input , back then we needed because we were making the first hiddne layer

classifier.add(Dense(6, kernel_initializer="uniform", activation = 'relu'))


# Adding the output layer
# sigmoid activation function gives us probabilities
# sigmoind function is the heart of probebilistic abbroach in the logstic  regresion model

# if having more 1 dependent var so units are that number lets say n categoroes and activartion is SOFTMAX

classifier.add(Dense(units = 1, kernel_initializer = 'uniform', activation = 'sigmoid'))

# Compiling the ANN
classifier.compile(loss='binary_crossentropy', optimizer='adam', metrics=['acc'])


# Fitting the ANN to the Training set
classifier.fit(X_train, y_train, batch_size = 10, epochs = 100)

# Part 3 - Making predictions and evaluating the model

# Predicting the Test set results
y_pred = classifier.predict(X_test)
# we need to put a threshold to say whats sensetive and what is not sensetive 
# 50 percent threshhold is fine 
y_pred = (y_pred > 0.5)



""" predict of the costumer down will leave the bank
Geography: France
Credit Score: 600
Gender: Male
age: 40
Tenure: 3
Balance: 60000
Number of Products: 2
Has Credit Card: Yes
Is Active Member:Yes
Estimated Salary: 50000"""




# Making the Confusion Matrix
from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)



# Part 4 evaluatiing improvign tuning ANN
# Evaluating the Ann
import keras
from keras.models import Sequential
from keras.layers import Dense
from keras import metrics
from keras.wrappers.scikit_learn import KerasClassifier
from sklearn.model_selection import cross_val_score
# now we start implementing Kfold validation inside Keras 

#KerasClassifier will require a FUnction as its first argument it buld the architecture of our neural network

def build_classifier():
    classifier = Sequential()
    classifier.add(Dense(6, kernel_initializer="uniform", activation = 'relu', input_dim = 11))

    # add second hidden layer 
    # we no longer need the input , back then we needed because we were making the first hiddne layer

    classifier.add(Dense(6, kernel_initializer="uniform", activation = 'relu'))


    # Adding the output layer
    # sigmoid activation function gives us probabilities
    # sigmoind function is the heart of probebilistic abbroach in the logstic  regresion model

    # if having more 1 dependent var so units are that number lets say n categoroes and activartion is SOFTMAX

    classifier.add(Dense(units = 1, kernel_initializer = 'uniform', activation = 'sigmoid'))

    # Compiling the ANN
    classifier.compile(loss='binary_crossentropy', optimizer='adam', metrics=['acc'])

    return classifier

# wrapp the whole thing by creating new classifier that is the global one. this one is the local one since it is 
    # inside the function
    # the training part is different no longer classifier.fit(X_train, y_train, batch_size = 10, epochs = 100)

classifier = KerasClassifier(build_fn = build_classifier, batch_size = 10, epochs = 100)

accuracies = cross_val_score( estimator = classifier , X = X_train, y = y_train, cv = 10, n_jobs = -1)

mean = accuracies.mean()
variance = accuracies.std()

# calculate the accuracy : numebr of correcnt predict / total numebr of predict 

#accuracy = (1550 + 175) / 2000

# given new data, we get 86 percent accuracy outputs 
#accuracy  



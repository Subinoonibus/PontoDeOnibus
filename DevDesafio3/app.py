from flask import Flask, render_template, request

app = Flask(__name__)
@app.route('/')
def home():
    return render_template('home.html')

@app.route('/mundo')
def contato():
    return render_template('world.html')

@app.route('/quemsomos')
def quemsomos():
    return render_template('quemsomos.html')
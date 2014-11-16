import json

__author__ = 'adrian'

from os import listdir, path
from tornado.ioloop import IOLoop
from tornado.web import RequestHandler, Application, url


class BotsListHandler(RequestHandler):
    def get(self):
        bots = [{'id': path.splitext(f)[0]} for f in listdir('bots')]
        self.write(json.dumps(bots))


class BotsHandler(RequestHandler):
    def get(self, bot_id):
        try:
            f = open('bots/%s.py' % bot_id)
            self.write(f.read())
        except IOError as e:
            self.write(json.dumps({'error': str(e)}))


application = Application([
    url(r"/rest/bots", BotsListHandler),
    url(r"/rest/bots/(.+)", BotsHandler),
])

if __name__ == "__main__":
    application.listen(8888)
    IOLoop.instance().start()

import json

__author__ = 'adrian'

from os import listdir
import tornado.ioloop
import tornado.web


class BotsList(tornado.web.RequestHandler):
    def get(self):
        bots = [f for f in listdir('bots')]
        self.write(json.dumps(bots))

application = tornado.web.Application([
    (r"/rest/bots/", BotsList),
])

if __name__ == "__main__":
    application.listen(8888)
    tornado.ioloop.IOLoop.instance().start()
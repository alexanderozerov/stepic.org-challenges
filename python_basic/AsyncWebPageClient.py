import requests
from queue import Queue
import threading
import time


class DownloadThread(threading.Thread):
    def __init__(self, queue, output):
        super(DownloadThread, self).__init__()
        self.queue = queue
        self.daemon = True
        self.output = output


    def run(self):
        while True:
            url = self.queue.get()
            try:
                response = requests.get(url, timeout=1)
                data = {
                    'status_code': response.status_code,
                    'url': url,
                    'content': response.content
                }
            except Exception as e:
                data = {
                    'status_code': -1,
                    'err': e,
                    'url': url
                }
            finally:
                self.output.append(data)
                self.queue.task_done()



class AsyncWebPageClient:
    def __init__(self, threads=2):
        self.threads = threads

    def get_pages(self, urls):
        queue = Queue()
        output = []
        for url in urls:
            queue.put(url)
        for i in range(self.threads):
            thread = DownloadThread(queue, output)
            thread.start()
        queue.join()
        downloads = []

        return output


urls = (
    'http://toly.github.io/blog/2014/02/13/parallelism-in-one-line/',
    'http://john16blog.blogspot.ru/2012/05/python-queue.html',
    'https://www.youtube.com/watch?v=oa9Tjs80n5E',
    'https://docs.python.org/3/library/asyncio-queue.html',
    'https://habraha3556br.ru/post/144850/',
    'https://habrahabr.ru/post/65617xxx/'
)
for i in range(10):
    start_time = time.time()
    client = AsyncWebPageClient()
    client.get_pages(urls)
    print(time.time() - start_time)

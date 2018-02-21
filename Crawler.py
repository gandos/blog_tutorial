import argparse
import sys
from http import client
from urllib.parse import urlsplit, parse_qs

parser = argparse.ArgumentParser(description="Simple Crawler build with python")
parser.add_argument('url',nargs="?", help="URL destination")
parser.add_argument('-p', '--p', help="url regex filter" )
parser.add_argument('-l', '--l', default=1, help="crawl deep level", type=int)
args = parser.parse_args()

if len(sys.argv) <= 1:
	parser.print_help()
	sys.exit(1)

if not args.url:
	print("URL is required")
	parser.print_help()
	sys.exit(1)

def main():
	url_o = urlsplit(args.url)
	print(url_o)
	
	if url_o.scheme == 'http':
		conn = client.HTTPConnection(url_o.netloc)
	else:
		print("using https")
		conn = client.HTTPSConnection(url_o.netloc)
	
	path = url_o.path
	
	if not path:
		path = "/"
		
	conn.request("GET", path)
	rsp = conn.getresponse()	
	data = rsp.read()
	
	print (rsp.status, rsp.getheaders())
	print(data)

if __name__ == "__main__":
	main()

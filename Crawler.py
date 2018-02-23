import argparse
import sys
import pathlib
from http import client
from urllib.parse import urlsplit, parse_qs
from lxml import etree, html
from time import gmtime, strftime
from time import sleep

parser = argparse.ArgumentParser(description="Simple Crawler build with python")
parser.add_argument('url',nargs="?", help="URL destination")
parser.add_argument('-p', '--pattern', help="url regex filter" )
parser.add_argument('-l', '--level', default=1, help="crawl deep level", type=int)
parser.add_argument('-d', '--delay', default=1, help="delay in seconds", type=int)
parser.add_argument('-o', '--output', default=".", help="output folder destination")
args = parser.parse_args()
o_folder = ""

if len(sys.argv) <= 1:
	parser.print_help()
	sys.exit(1)

if not args.url:
	print("URL is required")
	parser.print_help()
	sys.exit(1)

def send_request(url, fname):
	if not url:
		print("Empty url, aborting")
		return
	
	if not url.startswith("http"):
		url = "http://" + url
		
	url_o = urlsplit(url)
	
	if not url_o.netloc:
		print("Malforing url, aborting")
		return
	
	if url_o.scheme == 'http':
		conn = client.HTTPConnection(url_o.netloc)
	else:
		print("using https/tls")
		conn = client.HTTPSConnection(url_o.netloc)
	
	path = url_o.path
	
	if not path:
		path = "/"
		
	conn.request("GET", path)
	rsp = conn.getresponse()	
	doc = html.fromstring(rsp.read().decode("utf-8"))
	
	print (url, ': HTTP ', rsp.status, '-->', fname)
	#print(etree.tostring(doc, encoding='unicode', pretty_print=True))
	with open(fname, "w", encoding="utf-8") as file:
		file.write(etree.tostring(doc, encoding = 'unicode', pretty_print=True))

	res = []
	for child in doc.iter('a'):
		href = child.get("href")
		
		if href and not href == "#" and (not args.pattern or args.pattern in href):						
			hname = "unknown_"+strftime("%Y%m%d-%H%M%S", gmtime())
			
			h_arr = href.split( "/" )
			if len(h_arr) > 0:
				hname = h_arr[len(h_arr)-1]
				
				if not hname and len(h_arr) > 1:
					hname = h_arr[len(h_arr)-2]
			
			if "?" in hname:		
				hname = hname[0:hname.find("?")]
				
		
			if not href.startswith("http") and href.startswith("/"):
				href = url_o.scheme +"://" +url_o.netloc +href
			
			res.append(tuple((hname, href)))
	
	return res

def traverse(tuples, level):	
	if level <= args.level:
		for t in tuples:
			try:
				r = send_request(t[1], o_folder +'/' +t[0])
				
				sleep(args.delay)
			
				traverse(r, level+1)
			except Exception as e:
				print("error processing ", t[1])		
				print(e)
		
def request_test(url):
	url_o = urlsplit(url)
	conn = client.HTTPSConnection(url_o.netloc)
			
	conn.request("GET", url_o.path)
	rsp = conn.getresponse()	
	#print(rsp.read().decode('utf-8'))
	with open("./result.html", "w", encoding="utf-8") as file:
		file.write(rsp.read().decode("utf-8"))
	
def main():
	url = args.url
	
	global o_folder
	o_folder = args.output+'/'+strftime("%Y%m%d-%H%M%S", gmtime())
	
	pathlib.Path(o_folder).mkdir(parents=True, exist_ok=True) 
	
	t = tuple(('root',url))
	traverse([t], 1)
	#request_test("https://www.alphapolis.co.jp/novel/901123427/931067230")
	
if __name__ == "__main__":
	main()

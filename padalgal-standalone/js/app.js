const MOODS=[
 {id:"party",label:"Party",emoji:"🪩",glow:"#b53cff",desc:"Mass beats. Full volume."},
 {id:"love",label:"Love",emoji:"♥",glow:"#ff477e",desc:"Soft songs. Big feelings."},
 {id:"workout",label:"Workout",emoji:"⚡",glow:"#36d8a0",desc:"No skip. Keep moving."},
 {id:"retro",label:"Retro",emoji:"📻",glow:"#e2a55b",desc:"Raja, Rahman & old gold."},
 {id:"new",label:"New Release",emoji:"✦",glow:"#5b8cff",desc:"Fresh Tamil drops."},
 {id:"kuthu",label:"Kuthu",emoji:"🥁",glow:"#ff7b35",desc:"Feet on the floor."}
];
let currentMood="party", currentIndex=-1, playing=false, timer=null, progress=0;
const $=s=>document.querySelector(s);
function renderMoods(){ $("#moods").innerHTML=MOODS.map(m=>`<button class="mood ${m.id===currentMood?"active":""}" data-mood="${m.id}"><span class="emoji">${m.emoji}</span>${m.label}</button>`).join("");document.querySelectorAll(".mood").forEach(b=>b.onclick=()=>selectMood(b.dataset.mood));}
function renderPlaylist(){
 const list=PLAYLISTS[currentMood]||[];
 $("#sectionTitle").textContent=MOODS.find(m=>m.id===currentMood).label;
 $("#playlist").innerHTML=list.map((t,i)=>`<div class="track ${i===currentIndex?"playing":""}" data-i="${i}">
 <div class="track-num">${String(i+1).padStart(2,"0")}</div><div class="mini-cover">♫</div>
 <div class="track-main"><div class="track-title">${esc(t[0])}</div><div class="track-artist">${esc(t[1])} · ${esc(t[2])}</div></div><div class="track-more">•••</div></div>`).join("");
 document.querySelectorAll(".track").forEach(x=>x.onclick=()=>playTrack(+x.dataset.i));
}
function esc(s){return String(s).replace(/[&<>"']/g,c=>({"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"}[c]))}
function selectMood(id){currentMood=id;currentIndex=-1;document.documentElement.style.setProperty("--glow",MOODS.find(m=>m.id===id).glow);renderMoods();renderPlaylist();toast(`${MOODS.find(m=>m.id===id).label} playlist loaded`);}
function playTrack(i){currentIndex=i;playing=true;progress=0;const t=PLAYLISTS[currentMood][i];$("#nowTitle").textContent=t[0];$("#nowArtist").textContent=`${t[1]} · ${t[2]}`;$("#playBtn").textContent="Ⅱ";renderPlaylist();window.open("https://www.youtube.com/results?search_query="+encodeURIComponent(`${t[0]} ${t[1]} ${t[2]} Tamil song`),"_blank","noopener");toast("Opening the official/search listening source…");startProgress();}
function startProgress(){clearInterval(timer);timer=setInterval(()=>{if(!playing)return;progress+=.5;$("#progressFill").style.width=(progress%100)+"%";},1000)}
function next(){let n=currentIndex+1;if(n>=PLAYLISTS[currentMood].length)n=0;playTrack(n)}
function prev(){let n=currentIndex-1;if(n<0)n=PLAYLISTS[currentMood].length-1;playTrack(n)}
$("#shuffleBtn").onclick=()=>playTrack(Math.floor(Math.random()*PLAYLISTS[currentMood].length));
$("#playBtn").onclick=()=>{if(currentIndex<0)return playTrack(0);playing=!playing;$("#playBtn").textContent=playing?"Ⅱ":"▶"};
$("#nextBtn").onclick=next;$("#prevBtn").onclick=prev;
$("#openBtn").onclick=()=>currentIndex>=0&&window.open("https://www.youtube.com/results?search_query="+encodeURIComponent(PLAYLISTS[currentMood][currentIndex].join(" ")),"_blank","noopener");
$("#muteBtn").onclick=()=>toast("Padalgal uses YouTube/Spotify as the listening source; audio volume is controlled there.");
$("#searchBtn").onclick=()=>{$("#searchModal").hidden=false;$("#searchInput").focus()};
$("#closeSearch").onclick=()=>$("#searchModal").hidden=true;
$("#searchInput").oninput=e=>{const q=e.target.value.toLowerCase().trim();let out=[];for(const [m,arr] of Object.entries(PLAYLISTS))for(const t of arr)if((t[0]+" "+t[1]+" "+t[2]).toLowerCase().includes(q)&&q)out.push({m,t});$("#searchResults").innerHTML=out.slice(0,20).map(x=>`<div class="search-result"><b>${esc(x.t[0])}</b><br><small>${esc(x.t[1])} · ${esc(x.t[2])} · ${x.m}</small></div>`).join("")};
function toast(s){const x=$("#toast");x.textContent=s;x.classList.add("show");setTimeout(()=>x.classList.remove("show"),2200)}
renderMoods();renderPlaylist();document.documentElement.style.setProperty("--glow",MOODS[0].glow);

// Optional dynamic new-release mode. Set YOUTUBE_API_KEY in your own backend/proxy,
// never in this static client. The endpoint can return a curated JSON array.
async function refreshNewReleases(){
 try{
   const r=await fetch("api/new-releases.json",{cache:"no-store"}); if(!r.ok)return;
   const fresh=await r.json(); if(Array.isArray(fresh)&&fresh.length>=10) PLAYLISTS.new=fresh;
 }catch(e){}
}
refreshNewReleases();
